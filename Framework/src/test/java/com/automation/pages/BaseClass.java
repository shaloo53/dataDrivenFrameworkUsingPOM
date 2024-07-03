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
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class BaseClass {
	public WebDriver driver;
	public DataProviderExcel dataProviderExcel;
	public ConfigDataProvider config;
	public ExtentReports reports;
	public ExtentTest looger;

	@BeforeSuite
	public void setupSuite() throws IOException {
		dataProviderExcel = new DataProviderExcel();
		config = new ConfigDataProvider();
		reports = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(new File(System.getProperty("user.dir") + "/Reports/FreeCRM.html"));
		reports.attachReporter(spark);
	}

	@BeforeClass
	public void setUp() {
		driver = DriverFactory.startApplicaton(driver, config.getBrowser(), config.getURL());
	}

	@AfterMethod
	public void tearDownMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			String methodName = result.getName();
			File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") + "/screenshot/" + methodName + ".jpg";
			try {
				FileUtils.copyFile(f, new File(path));
				looger.fail("fail", MediaEntityBuilder.createScreenCaptureFromPath(path).build());

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
