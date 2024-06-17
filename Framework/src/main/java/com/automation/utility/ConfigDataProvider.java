package com.automation.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigDataProvider {
	Properties prop ;
public ConfigDataProvider() throws IOException {
	prop = new Properties();
	FileInputStream fs = new  FileInputStream(new File	("./config/Config.properties"));
	prop.load(fs);
	
}

	public String getBrowser() {
		return prop.getProperty("Browser");
	}

	public String getURL() {
		return prop.getProperty("URL");
	}
}
