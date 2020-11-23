package com.expedia.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSExecutor {
	
	private static JSExecutor jsexecutor = null;
	private static JavascriptExecutor jsdriver;

	private JSExecutor(WebDriver driver) {
		JSExecutor.jsdriver = (JavascriptExecutor)driver;
	}
	
	public static JSExecutor getJSExecutor(WebDriver driver) {
		if(jsexecutor==null) {
			return new JSExecutor(driver);
		}
		return jsexecutor;
	}
	
	
	public boolean isPageLoaded() {
		return jsdriver.executeScript("return document.readyState").equals("complete");
	}
	
	public static void scrollWindow(int horizontal,int vertical) {
		jsdriver.executeScript("window.scrollBy("+horizontal+","+vertical+");");
	}
	
	public static String getTitle() {
		return jsdriver.executeScript("return document.title").toString();
	}
	
	public static void sendKeys(WebElement element, String charSeq) {
		jsdriver.executeScript("arguments[0].value='"+charSeq+"'", element);
	}
	
	public static void click(WebElement element) {
		jsdriver.executeScript("arguments[0].click();", element);
	}
	
	public static void refreshBrowser() {
		jsdriver.executeScript("history.go(0)");
	}
	
	public static void goBack() {
		jsdriver.executeScript("window.history.back()");
	}
	
	public static void goForward() {
		jsdriver.executeScript("window.history.forward()");
	}
	
	public static long getScrollHeight() {
		return (long) jsdriver.executeScript("return document.body.scrollHeight");
	}
	
	public static void scrollToBottomOfPage() {
		long pageHeight = (Long) jsdriver.executeScript("return document.body.scrollHeight;");

		while (true) {
			jsdriver.executeScript("window.scrollTo(0,document.body.scrollHeight);");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			long newHeight = (Long) jsdriver.executeScript("return document.body.scrollHeight;");
			if (pageHeight == newHeight) {
				break;
			}
			pageHeight = newHeight;
		}
	}

}
