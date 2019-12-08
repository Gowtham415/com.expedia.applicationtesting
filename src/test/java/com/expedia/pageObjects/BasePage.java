package com.expedia.pageObjects;

import org.openqa.selenium.WebDriver;

import com.expedia.utilities.ExplicitWaitsUtility;

public class BasePage extends Page{
	
	ExplicitWaitsUtility driverWait;
	public BasePage(WebDriver driver) {
		super(driver);
		driverWait= new ExplicitWaitsUtility(driver);
	}
}
