package com.browserstack.utilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

class ChromeLatestWin10 implements Runnable {
	public void run() {
		mainTestClass r1 = new mainTestClass();
		try {
			r1.executeTest("Chrome", "latest", "Windows", "10", "Thread 1", "Multithreaded Java sample build");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
class FirefoxLatestWin10 implements Runnable {
	public void run() {
		mainTestClass r1 = new mainTestClass();
		try {
			r1.executeTest("Firefox", "latest", "Windows", "10", "Thread 2", "Multithreaded Java sample build");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
class IELatestWin10 implements Runnable {
	public void run() {
		mainTestClass r1 = new mainTestClass();
		try {
			r1.executeTest("IE", "latest", "Windows", "10", "Thread 3", "Multithreaded Java sample build");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
class SafariMacCatalina implements Runnable
{
	public void run() {
		mainTestClass r1 = new mainTestClass();
		try {
			r1.executeTest("Safari", "latest", "OS X", "Catalina", "Thread 4", "Multithreaded Java sample build");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
public class mainTestClass {
	public static final String USERNAME = "haideralikhan3";
	public static final String AUTOMATE_KEY = "HSse2q1pKNq3PsxqNrNc";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	public static void main(String[] args) throws Exception {
		Thread object1 = new Thread(new ChromeLatestWin10());
		object1.start();
		Thread object2 = new Thread(new FirefoxLatestWin10());
		object2.start();
		Thread object3 = new Thread(new IELatestWin10());
		object3.start();
		Thread object4 = new Thread(new SafariMacCatalina());
		object4.start();
	}
	public void executeTest(String browser, String version, String os, String os_version, String test_name, String build_name) throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browser", browser);
		caps.setCapability("browser_version", version);
		caps.setCapability("os", os);
		caps.setCapability("os_version", os_version);
		caps.setCapability("build", build_name);
		caps.setCapability("name", test_name);
		WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		// Searching for 'BrowserStack' on google.com
		driver.get("https://www.google.com");
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("BrowserStack");
		element.submit();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		System.out.println(driver.getTitle());
		// Setting the status of test as 'passed' or 'failed' based on the condition; if title of the web page matches 'BrowserStack - Google Search'
		if (driver.getTitle().equals("BrowserStack - Google Search")) {
			jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Title matched!\"}}");
		}
		else {
			jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Title not matched\"}}");
		}
		driver.quit();
	}
}

