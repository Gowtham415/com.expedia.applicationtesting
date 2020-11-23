package com.expedia.utilities;

import java.util.function.Function;

import org.openqa.selenium.By;
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
	
	public WebElement waitUntilElementisVisible(By locator){
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public WebElement waitUntilElementisClickable(By locator){
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public WebElement waitForElement(final By locator) {
		return wait.until(new Function<WebDriver,WebElement>(){

			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
			
		});
	}
}
