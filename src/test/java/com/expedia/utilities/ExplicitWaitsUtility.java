package com.expedia.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWaitsUtility {
	
	public WebDriver driver;
	public static WebDriverWait wait;
	
	public ExplicitWaitsUtility(WebDriver driver){
		this.driver=driver;
		wait = new WebDriverWait(driver,15);
	}
	
	public WebElement waitUntilElementisVisible(WebElement element){
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public WebElement waitUntilElementisClickable(WebElement element){
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
}
