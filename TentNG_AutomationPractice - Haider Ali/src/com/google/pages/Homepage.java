package com.google.pages;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

public class Homepage {
	WebDriver driver;

	@FindBy(xpath="//*[@name='q']")
	WebElement googleSearchBox;

	public Homepage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void searchText(String text) {
		isLoaded(googleSearchBox);
		googleSearchBox.sendKeys(text);
		googleSearchBox.submit();
	}
		
	public String getPageTitle() {
		return driver.getTitle();
	}
	//To check the element exists or not and using Fluent Wait to check for 30 sec max at the intervals of 1 sec
	@SuppressWarnings("deprecation")
	public void isLoaded(WebElement element) throws Error {
		new FluentWait<WebDriver>(driver)
		.withTimeout(30, TimeUnit.SECONDS)
		.pollingEvery(1, TimeUnit.SECONDS)
		.ignoring(NoSuchElementException.class)
		.ignoring(StaleElementReferenceException.class)
		.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver webDriver) {
				return element != null && element.isDisplayed();
			}
		});
	}
}
