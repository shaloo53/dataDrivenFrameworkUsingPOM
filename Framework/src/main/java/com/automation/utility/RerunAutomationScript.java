package com.automation.utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RerunAutomationScript implements IRetryAnalyzer {

	@Override
	public boolean retry(ITestResult result) {
		
		int maxRetryCount = 2;
		int retryCount =0;
		if(retryCount<maxRetryCount) {
			retryCount++;
			return true;
		}
		return false;
	}

}
