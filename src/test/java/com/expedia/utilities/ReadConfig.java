package com.expedia.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


/*
 * To Read the variables from Configuration file
 */
public class ReadConfig {

	Properties property;

	public ReadConfig() {
		File fs = new File("./Configuration\\config.properties");
		try {
			FileInputStream fis = new FileInputStream(fs);
			property = new Properties();
			property.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is :" + e.getMessage());
		}
	}

	public String getChromeDriverPath() {
		return property.getProperty("chromedriver");
	}

	public String getBaseUrl() {
		return property.getProperty("baseurl");
	}

	public String getOriginCity() {
		return property.getProperty("originCity");
	}

	public String getDestinationCity() {
		return property.getProperty("Destination");
	}

	public String getDepartureDate() {
		return property.getProperty("departuredate");
	}

	public String getReturnDate() {
		return property.getProperty("returndate");
	}

	// Like the Above method add all the methods for all variables in
	// config.properties file.
}
