package com.automationPractice.tests.signIn;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automationPractice.pages.SignInPage;
import com.automationPractice.pages.SignUpPage;
import com.automationPractice.utilities.ExcelFileDataProvider;

import io.github.bonigarcia.wdm.WebDriverManager;

public class signIn {

	public WebDriver driver;
	String baseURL = "http://automationpractice.com/";
	SignUpPage signUpPage;
	SignInPage signInPage;

	@BeforeClass
	public void beforeClass() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@BeforeMethod
	public void navigateToWebsite() {
		driver.get(baseURL);
		driver.manage().window().maximize();
		signUpPage = new SignUpPage(driver);
		signInPage = new SignInPage(driver);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	@Test(dataProvider="TestData", dataProviderClass = ExcelFileDataProvider.class, priority = 2)
	public void signInUsers(String firstName,String lastName,String email,String	password,String address,String city,String state,String postalCode,String phone) {
		signInPage.clickSignInHeaderButton();
		signInPage.clickSignInButton(email, password);
		Assert.assertEquals(signInPage.getAccountTitleNameText(), firstName+" "+lastName);
		signUpPage.clickSignOutButton();
		System.out.println("SignIn Complete for: "+email);
	}
	
	@Test(dataProvider="TestData", dataProviderClass = ExcelFileDataProvider.class,priority = 1)
	public void signUpUsers(String firstName,String lastName,String email,String	password,String address,String city,String state,String postalCode,String phone) {
		signInPage.clickSignInHeaderButton();
		Assert.assertEquals(signInPage.getPageTitle(), "Login - My Store");
		signInPage.clickSignUpButton(email);
		signUpPage.setFirstName(firstName);
		signUpPage.setLastName(lastName);
		signUpPage.setEmail(email);
		signUpPage.setPassword(password);
		signUpPage.setAddress1(address);
		signUpPage.setCity(city);
		signUpPage.setState(state);
		signUpPage.setPostalCode(postalCode);
		signUpPage.setPhone(phone);
		signUpPage.clickRegisterButton();
		Assert.assertEquals(signInPage.getAccountTitleNameText(), firstName+" "+lastName);
		signUpPage.clickSignOutButton();
		System.out.println("SignUp Complete for: "+email);
	}

}
