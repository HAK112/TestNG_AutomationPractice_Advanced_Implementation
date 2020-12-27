package com.automationPractice.tests.parallelExecution;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ParallelExecutionTestNG {

	String baseURL = "http://automationpractice.com/";
	String baseURL2 = "http://google.com/";

	@Test(enabled = true)
	public void chromeSession() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get(baseURL);
	}

	@Test(enabled = true)
	public void edgeSession() {
		WebDriverManager.edgedriver().setup();
		WebDriver driver = new EdgeDriver();
		driver.get(baseURL);
	}
	
	@Test(enabled = true)
	public void operaSession() {
		WebDriverManager.operadriver().setup();
		WebDriver driver = new OperaDriver();
		driver.get(baseURL2);
	}
	
	@Test(enabled = true)
	public void firefoxSession() {
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		driver.get(baseURL2);
	}
}
