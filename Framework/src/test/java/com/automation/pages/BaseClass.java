package com.automation.pages;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.automation.utility.ConfigDataProvider;
import com.automation.utility.DriverFactory;
import com.automation.utility.ExcelReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.io.Files;

public class BaseClass {
	public WebDriver driver;
	public ExcelReader excelReader;
	public ConfigDataProvider config;
	public ExtentReports reports;
	public ExtentTest looger;

	@SuppressWarnings("deprecation")
	@BeforeSuite
	public void setupSuite() throws IOException {
		excelReader = new ExcelReader();
		config = new ConfigDataProvider();
		reports = new ExtentReports();
		ExtentHtmlReporter htmlreport = new ExtentHtmlReporter(
				new File(System.getProperty("user.dir") + "/Reports/FreeCRM.html"));
		reports.attachReporter(htmlreport);

	}

	@BeforeClass
	public void setUp() {
		driver = DriverFactory.startApplicaton(driver, config.getBrowser(), config.getURL());
	}

	@AfterMethod
	public void tearDonwMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			String methodName = result.getName();
			File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") + "/screenshot/" + methodName + ".jpg";
			try {
				FileUtils.copyFile(f, new File(path));
				looger.fail("login fail", MediaEntityBuilder.createScreenCaptureFromPath(path).build());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@AfterClass
	public void tearDown() {
		DriverFactory.quitBrowser(driver);
	}

	@AfterSuite
	public void tearDownAfterSuite() {
		reports.flush();
	}
}
