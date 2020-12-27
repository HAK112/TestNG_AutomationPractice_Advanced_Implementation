package com.tinyUpload.tests.fileUpload;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tinyUpload.pages.HomePage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutoitDemo {
	public WebDriver driver;
	String baseURL = "http://www.tinyupload.com/";
	String baseFileUploadURL = System.getProperty("user.dir");
	HomePage homepage;

//	@AfterSuite
//	public void beforeSuite() {
//		try {
//			//For Delay between Suites
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		};
//	}
	@BeforeClass
	public void beforeClass() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@BeforeMethod
	public void navigateToWebsite() {
		driver.manage().window().maximize();
		driver.get(baseURL);
		homepage = new HomePage(driver);
//		try {
//			//For Delay between Methods
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}	
	
//	@AfterTest
//	public void afterTest() {
//		try {
//			//For Delay between every tests
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		};
//	}	
	
	@Test
	public void test() {
		Assert.assertTrue(homepage.getPageTitle().contains("TinyUpload.com"));
		//Homepage.uploadFile(baseFileUploadURL+"\\resource\\FileUploader.exe");
		homepage.clickUploadFileButton();
		try {
			String path = baseFileUploadURL+"\\resource\\FileUploader.exe";
			Runtime.getRuntime().exec(path);
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		//Assert.assertEquals(homepage.getUploadedFileTest(),"ReleaseNotes.docx");
		System.out.println("Test Completed");
	}
}
