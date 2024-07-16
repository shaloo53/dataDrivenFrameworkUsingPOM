package com.automation.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class LoginPage {
WebDriver driver;
WebDriverWait wait;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	// TODO Auto-generated constructor stub
}
	
	@FindBy(xpath="//*[(text()='Log In')]")
	private WebElement logInButton;

	@FindBy(name = "email")
	private WebElement name;
	
	@FindBy(name="password")
	private WebElement pass;
	
	@FindBy(xpath="//*[text()='Login']")
	private WebElement login;
	
	@FindBy(xpath = "//*[contains(@class,'ui negative message')]")
	private WebElement errorMsg;
	
	public void clickOnLoginButton() {
		logInButton.click();
		
	}
	public void loginToCRM(String uname,String pswd) {
		wait.until(ExpectedConditions.visibilityOf(name));
		name.sendKeys(uname);
		pass.sendKeys(pswd);
		login.click();
		
	}

	
	public String geterrorMessageWhileLogIn() {
		// TODO Auto-generated method stub
		return  errorMsg.getText();
	}
}
