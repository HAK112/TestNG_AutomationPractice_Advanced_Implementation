package com.automationPractice.utilities;

import java.io.File;
import java.util.Arrays;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.automationPractice.tests.signIn.signUp;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.google.common.io.Files;

public class TestListeners implements ITestListener{
	
	private static ExtentReports extent = ExtentManager.createInstance();
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) {
		ExtentTest test = extent.createTest(result.getTestClass().getName()+"::"+result.getMethod().getMethodName());
		extentTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		String logText = "<b>Test Method "+ result.getMethod().getMethodName() + "Successfull</b>";
		Markup markup = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		extentTest.get().log(Status.PASS,markup);
	}

	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		extentTest.get().fail("<details><summary><b><font color=red>Exception Occured, Click to see Details:</font></b></summary>"+exceptionMessage.replaceAll(",", "<br>")+"</details>\n");
		WebDriver driver = ((signUp)result.getInstance()).driver;
		String path = takeScreenshot(driver,result.getMethod().getMethodName());
		try {
			extentTest.get().fail("<br><font color=red>"+"Screenshot of Failure"+"</font></b>",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}catch(Exception e) {
			extentTest.get().fail("Test Failed, Cannot Attach Screenshot");
		}
		String logText = "<b>Test Method"+methodName+" Failed</br>";
		Markup markup = MarkupHelper.createLabel(logText, ExtentColor.RED);
		extentTest.get().log(Status.FAIL, markup);
	}

	public void onTestSkipped(ITestResult result) {
		String logText = "<b>Test Method "+ result.getMethod().getMethodName() + "Skipped</b>";
		Markup markup = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		extentTest.get().log(Status.SKIP,markup);
	}
	
	public void onFinish(ITestContext context) {
		if(extent!=null)
			extent.flush();
	}
	
	public String takeScreenshot(WebDriver driver, String methodName) {
		String fileName = getScreenshotName(methodName);
		String directory = System.getProperty("user.dir")+"/screenshots/";
		new File(directory).mkdirs();
		String path = directory+fileName;
		try {
			File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			Files.copy(screenshot,  new File(path));
			System.out.println("******************************************************");
			System.out.println("Screenshot Stored At: "+path);
			System.out.println("******************************************************");
		}catch (Exception e ) {
			e.printStackTrace();
		}
		return path;
	}
	
	public static String getScreenshotName(String methodName) {
		Date date = new Date();
		String filename = methodName+"_"+date.toString().replace(":", "_").replace(" ", "_")+".jpg";
		return filename;
	}

}
