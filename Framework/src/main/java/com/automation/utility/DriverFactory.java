package com.automation.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public static WebDriver startApplicaton(WebDriver driver,String browser, String URL) {
		if(browser.equalsIgnoreCase("Chrome")) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		}
		else {
			System.out.println("we do not support this browser.");
		}
		driver.manage().window().maximize();
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		return driver;

		}
	public static void quitBrowser(WebDriver driver) {
		driver.quit();
	}

}
