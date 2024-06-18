package com.automation.testcases;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.support.PageFactory;

import com.automation.pages.BaseClass;
import com.automation.pages.LoginPage;
import com.automation.utility.RerunAutomationScript;

@Test(retryAnalyzer = RerunAutomationScript.class)
public class LoginTestCRM extends BaseClass {
	
	public void verifyUserAbleToLaunchTheURL() throws InterruptedException {
		looger = reports.createTest("Login To CRM");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		looger.info("url launched");
		loginPage.clickOnLoginButton();
		loginPage.loginToCRM(excelReader.getDataFromExcelSheet(0,0),excelReader.getDataFromExcelSheet(0,1));
		assertTrue(loginPage.geterrorMessageWhileLogIn().contains("Invalidlogin"), "Verify the error mesaage.");
		looger.pass("login success");
		
	}

}
