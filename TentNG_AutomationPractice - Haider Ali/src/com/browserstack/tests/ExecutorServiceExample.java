package com.browserstack.tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ExecutorServiceExample {
	public static final String USERNAME = "haideralikhan3";
	public static final String AUTOMATE_KEY = "HSse2q1pKNq3PsxqNrNc";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newFixedThreadPool(2);  // A pool of 2 threads are being created here. You can change this as per your parallel limit
		Set<Callable<String>> callables = new HashSet<Callable<String>>();
		ExecutorServiceExample obj1 = new ExecutorServiceExample();
		callables.add(new Callable<String>() {
			public String call() throws Exception {
				obj1.executeTest("Chrome", "latest", "Windows", "10", "Test 1", "Java Executor Sample Build");
				return "Task 1 completed";
			}
		});
		callables.add(new Callable<String>() {
			public String call() throws Exception {
				obj1.executeTest("Firefox", "latest", "Windows", "10", "Test 2", "Java Executor Sample Build");
				return "Task 2 completed";
			}
		});
		callables.add(new Callable<String>() {
			public String call() throws Exception {
				obj1.executeTest("IE", "latest", "Windows", "10", "Test 3", "Java Executor Sample Build");
				return "Task 3 completed";
			}
		});
		callables.add(new Callable<String>() {
			public String call() throws Exception {
				obj1.executeTest("Safari", "latest", "OS X", "Catalina", "Test 4", "Java Executor Sample Build");
				return "Task 4 completed";
			}
		});
		// You can add as many test functions as Callables as you want
		List<Future<String>> futures;
		futures = executorService.invokeAll(callables);
		for(Future<String> future : futures){
			System.out.println("future.get = " + future.get());
		}
		executorService.shutdown();
	}
	public void executeTest(String browser, String version, String os, String os_version, String test_name, String build_name) {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browser", browser);
		caps.setCapability("browser_version", version);
		caps.setCapability("os", os);
		caps.setCapability("os_version", os_version);
		caps.setCapability("build", build_name);
		caps.setCapability("name", test_name);
		WebDriver driver = null;
		try {
			driver = new RemoteWebDriver(new URL(URL), caps);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

