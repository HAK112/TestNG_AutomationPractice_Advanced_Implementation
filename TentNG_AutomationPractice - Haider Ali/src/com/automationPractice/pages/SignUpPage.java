package com.automationPractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SignUpPage extends BasePage{
	WebDriver driver;
	
	@FindBy(xpath="//input[@id='id_gender1']")
	WebElement titleOptionMr;
	
	@FindBy(xpath="//input[@id='id_gender2']")
	WebElement titleOptionMrs;
	
	@FindBy(xpath="//input[@id='customer_firstname']")
	WebElement firstNameInputField;
	
	@FindBy(xpath="//input[@id='customer_lastname']")
	WebElement lastNameInputField;
	
	@FindBy(xpath="//input[@id='email']")
	WebElement emailInputField;
	
	@FindBy(xpath="//input[@id='passwd']")
	WebElement passwordInputField;
	
	@FindBy(xpath="//input[@id='days']")
	WebElement dateOfBirthDaysSelector;
	
	@FindBy(xpath="//input[@id='months']")
	WebElement dateOfBirthMonthsSelector;	
	
	@FindBy(xpath="//input[@id='years']")
	WebElement dateOfBirthYearsSelector;	
	
	@FindBy(xpath="//input[@id='address1']")
	WebElement streetAddress1InputField;
	
	@FindBy(xpath="//input[@id='address2']")
	WebElement appartmentNumberAddress2InputField;
	
	@FindBy(xpath="//input[@id='city']")
	WebElement cityInputField;
	
	@FindBy(xpath="//select[@id='id_state']")
	WebElement stateSelector;
	
	@FindBy(xpath="//input[@id='postcode']")
	WebElement postalCodeInputField;
	
	@FindBy(xpath="//input[@id='phone']")
	WebElement phoneInputField;
	
	@FindBy(xpath="//input[@id='alias']")
	WebElement aliasInputField;
	
	@FindBy(xpath="//button[@id='submitAccount']")
	WebElement registerButton;
	
	Select dateOfBirthDays,dateOfBirthMonths,dateOfBirthYears,state;
		
	public SignUpPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickTitleMr() {
		isLoaded(titleOptionMr);
		titleOptionMr.click();
	}
	
	public void clickTitleMrs() {
		isLoaded(titleOptionMrs);
		titleOptionMrs.click();
	}
	
	public void setFirstName(String firstname) {
		isLoaded(firstNameInputField);
		firstNameInputField.sendKeys(firstname);
	}
	
	public void setLastName(String lastName) {
		isLoaded(lastNameInputField);
		lastNameInputField.sendKeys(lastName);
	}
	
	public void setEmail(String email) {
		isLoaded(emailInputField);
		emailInputField.clear();
		emailInputField.sendKeys(email);
	}
	
	public void setPassword(String password) {
		isLoaded(passwordInputField);
		passwordInputField.clear();
		passwordInputField.sendKeys(password);
	}
	
	public void setDateOfBirthDays(String days) {
		isLoaded(dateOfBirthDaysSelector);
		dateOfBirthDays = new Select(dateOfBirthDaysSelector);
		dateOfBirthDays.selectByValue(days);
	}
	
	public void setDateOfBirthMonths(String months) {
		isLoaded(dateOfBirthMonthsSelector);
		dateOfBirthMonths = new Select(dateOfBirthMonthsSelector);
		dateOfBirthMonths.selectByValue(months);
	}
	
	public void setDateOfBirthYears(String years) {
		isLoaded(dateOfBirthMonthsSelector);
		dateOfBirthYears = new Select(dateOfBirthYearsSelector);
		dateOfBirthYears.selectByValue(years);
	}
	
	public void setAddress1(String address) {
		isLoaded(streetAddress1InputField);
		streetAddress1InputField.sendKeys(address);
	}
	
	public void setAddress2(String address) {
		isLoaded(appartmentNumberAddress2InputField);
		appartmentNumberAddress2InputField.sendKeys(address);
	}
	
	public void setCity(String city) {
		isLoaded(cityInputField);
		cityInputField.sendKeys(city);
	}
	
	public void setState(String state) {
//		isLoaded(stateSelector);
		this.state = new Select(stateSelector);
		this.state.selectByVisibleText(state);
	}
	
	public void setPostalCode(String postCode) {
		isLoaded(postalCodeInputField);
		postalCodeInputField.sendKeys(postCode);
	}
	
	public void setPhone(String phone) {
		isLoaded(phoneInputField);
		phoneInputField.sendKeys(phone);
	}
	
	public void setAlias(String alias) {
		isLoaded(aliasInputField);
		aliasInputField.sendKeys(alias);
	}
	
	public void clickRegisterButton() {
		isLoaded(registerButton);
		registerButton.click();
	}
	
	public String getSignInPageTitle() {
		return driver.getTitle();
	}
}