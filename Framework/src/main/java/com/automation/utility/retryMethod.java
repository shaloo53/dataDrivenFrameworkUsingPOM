package com.automation.utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class retryMethod implements IRetryAnalyzer{

	private int count = 0;
	private final int maxRetry =3;
	
	@Override
	public boolean retry(ITestResult result) {
		if(count<maxRetry) {
			count++;
			return true;
		}
		return false;
	}
}
