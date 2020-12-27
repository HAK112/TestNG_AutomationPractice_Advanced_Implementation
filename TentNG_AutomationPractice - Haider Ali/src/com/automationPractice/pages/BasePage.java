package com.automationPractice.pages;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

public class BasePage {
	WebDriver driver;
	//SignIn Button
	@FindBy(xpath="//a[@class='login']")
	WebElement signInButton;
	
	@FindBy(xpath="//a[@class='logout']")
	WebElement logoutButton;
	
	@FindBy(xpath="//a[@class='account']//span")
	WebElement accountTitleName;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickSignInHeaderButton() {
		isLoaded(signInButton);
		signInButton.click();
	}
	
	public void clickSignOutButton() {
		isLoaded(logoutButton);
		logoutButton.click();
	}
	
	public String getAccountTitleNameText() {
		isLoaded(accountTitleName);
		return accountTitleName.getText();
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
