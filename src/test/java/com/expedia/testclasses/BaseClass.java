package com.expedia.testclasses;

import static com.expedia.utilities.ScreenShots.captureScreenshot;

import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.expedia.pageobjects.Page;
import com.expedia.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public  ThreadLocal<WebDriver> webdriver = new ThreadLocal<WebDriver>();
	public static WebDriver driver;
	public static String baseurl;
	public static Logger logger = LogManager.getLogger(BaseClass.class.getName());

	public static ReadConfig readConfig = new ReadConfig();
	public Page page;

	/*
	 * Variables for Extents Reports.
	 */


	@BeforeTest
	public void beforeTest() {

	}

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();

		// To Remove Info tab "Chrome is controlled by Automated Software "
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
	
		webdriver.set(new ChromeDriver(options));
		driver = webdriver.get();
		page = new Page(driver);
		baseurl = readConfig.getBaseUrl();
		logger.info("Getting the base URL from Config File");
		driver.manage().window().maximize();
		driver.get(baseurl);
		logger.info("Opening the base URL");

	}

	@AfterMethod
	public void tearDown(ITestResult tr) {
		if (tr.getStatus() == ITestResult.FAILURE) {
			captureScreenshot(driver, tr.getName());
		}
		driver.close();
	}

	// Method to check if any alert is present
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}

	}

}
