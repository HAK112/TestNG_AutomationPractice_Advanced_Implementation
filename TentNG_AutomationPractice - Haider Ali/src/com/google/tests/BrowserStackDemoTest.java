package com.google.tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.pages.Homepage;

public class BrowserStackDemoTest {
	public static final String AUTOMATE_USERNAME = "haideralikhan3";
	public static final String AUTOMATE_ACCESS_KEY = "HSse2q1pKNq3PsxqNrNc";
	public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";
	WebDriver driver;
	Homepage homepage;
	@BeforeClass
	public void setUp() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browserName", "iPhone");
		caps.setCapability("device", "iPhone 11");
		caps.setCapability("realMobile", "true");
		caps.setCapability("os_version", "14.0");
		caps.setCapability("name", "BStack-[Java] Sample Test"); // test name
		caps.setCapability("build", "BStack Build Number 1"); // CI/CD job or build name
		try {
			driver = new RemoteWebDriver(new URL(URL), caps);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.get("https://www.google.com");
		homepage = new Homepage(driver);
	}

	@Test
	public void test(){
		homepage.searchText("BrowserStack");
		System.out.println(driver.getTitle());
		// Setting the status of test as 'passed' or 'failed' based on the condition; if title of the web page starts with 'BrowserStack'
		if (homepage.getPageTitle().substring(0,12).equals("BrowserStack")) {
			markTestStatus("passed","Yaay title matched!",driver);
		}
		else {
			markTestStatus("failed","Naay title did not match!",driver);
		}
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	// This method accepts the status, reason and WebDriver instance and marks the test on BrowserStack
	public static void markTestStatus(String status, String reason, WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \""+status+"\", \"reason\": \""+reason+"\"}}");
	}
}
