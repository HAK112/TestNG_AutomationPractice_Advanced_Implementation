package com.tinyUpload.pages;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

public class HomePage {
	WebDriver driver;

	@FindBy(xpath="//input[@name='uploaded_file']")
	WebElement chooseFileButton;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void uploadFile(String fileLocation) {
		isLoaded(chooseFileButton);
		chooseFileButton.sendKeys(fileLocation);
	}
	
	public void clickUploadFileButton() {
		isLoaded(chooseFileButton);
		Actions builder = new Actions(driver);
		builder.moveToElement(chooseFileButton).click().build().perform();
	}
	
	public String getUploadedFileTest() {
		isLoaded(chooseFileButton);
		return chooseFileButton.getText();
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
