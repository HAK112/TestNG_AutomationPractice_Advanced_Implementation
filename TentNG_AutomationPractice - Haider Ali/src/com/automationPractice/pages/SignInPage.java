package com.automationPractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends BasePage{
	WebDriver driver;
	
	//Email Input
	@FindBy(xpath="//input[@id='email']")
	WebElement loginEmailInputField;
	
	//Password Input
	@FindBy(xpath="//input[@id='passwd']")
	WebElement passwordInputField;
	
	//Sign In Button
	@FindBy(xpath="//button[@id='SubmitLogin']")
	WebElement signInButton;
	
	//Sign Up Button
	@FindBy(xpath="//button[@id='SubmitCreate']")
	WebElement signUpButton;
	
	//Sign Up Button
	@FindBy(xpath="//input[@name='email_create']")
	WebElement signUpEmailField;	

	public SignInPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	private void setEmail(String data) {
		isLoaded(loginEmailInputField);
		loginEmailInputField.sendKeys(data);
	}
	
	private void setPassword(String data) {
		isLoaded(passwordInputField);
		passwordInputField.sendKeys(data);
	}
	
	public void clickSignInButton(String email, String password) {
		isLoaded(signInButton);
		setEmail(email);
		setPassword(password);
		signInButton.click();
	}
	
	private void setSignUpEmail(String email) {
		isLoaded(signUpEmailField);
		signUpEmailField.sendKeys(email);
	}
	
	public void clickSignUpButton(String email) {
		isLoaded(signUpButton);
		setSignUpEmail(email);
		signUpButton.click();
	}
	
	public String getSignInPageTitle() {
		return driver.getTitle();
	}
}