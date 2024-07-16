package com.automation.utility;

import java.time.Duration;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public static WebDriver startApplicaton(WebDriver driver,String browser, String URL) {
		if(browser.equalsIgnoreCase("Chrome")) {
		driver = new ChromeDriver();
		}
		else {
			System.out.println("we do not support this browser.");
		}
		driver.manage().window().maximize();
		driver.get(URL);
		//for switching to new window
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get(URL);
		
		ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("Windows are "+windows);
		driver.switchTo().window(windows.get(0));
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		
		/*
		 * WebElement fs = driver.findElement(By.id("login"));
		 * 
		 * FluentWait<WebDriver> wait = new
		 * FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(1)).pollingEvery
		 * (Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
		 * wait.until(ExpectedConditions.visibilityOf(fs));
		 * 
		 * WebDriverWait wait1 =new WebDriverWait(driver, Duration.ofSeconds(10));
		 * wait1.until(ExpectedConditions.visibilityOf(fs)); fs.click();
		 * 
		 */	
		return driver;

		
		}
	public static void quitBrowser(WebDriver driver) {
		driver.quit();
	}

}
