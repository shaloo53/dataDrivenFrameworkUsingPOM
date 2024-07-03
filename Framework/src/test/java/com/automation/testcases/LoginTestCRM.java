package com.automation.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import com.automation.pages.BaseClass;
import com.automation.pages.LoginPage;
import com.automation.utility.ExcelReader;

public class LoginTestCRM extends BaseClass {
	ExcelReader excel = new ExcelReader();

	@DataProvider(name = "readDataFromExcel1")
	public Object[][] readData() {
		int totalRow = excel.totalRow();
		int totalColumn = excel.totalColumn();
		Object[][] ob = new Object[totalRow - 1][totalColumn];

		for (int i = 1; i < totalRow; i++) {
			for (int j = 0; j < totalColumn; j++) {
				ob[i - 1][j] = excel.getDataFromExcelSheet(i, j);

			}
		}

		return ob;
	}

	@Test(dataProvider = "readDataFromExcel1")
	public void verifyUserAbleToLaunchTheURL(String UserName, String Password) {
		LoginPage loginPage = new LoginPage(driver);

		looger = reports.createTest("Login To CRM");
		looger.info("url launched");

		loginPage.clickOnLoginButton();
		loginPage.loginToCRM(UserName, Password);
		assertTrue(loginPage.geterrorMessageWhileLogIn().contains("Invalid login"), "Verify the error mesaage.");
		looger.pass("Unable to login");

	}

}
