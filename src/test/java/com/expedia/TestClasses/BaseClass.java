package com.expedia.TestClasses;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.expedia.utilities.ReadConfig;

public class BaseClass {

	public static WebDriver driver;
	public static String baseurl;
	public static Logger logger = LogManager.getLogger(BaseClass.class.getName());

	public static ReadConfig readConfig = new ReadConfig();

	
	/*
	 * Variables for Extents Reports.
	 */
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	@BeforeTest
	public void beforeTest() {
		// For Extent reporting 
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
		String extentReportPath = System.getProperty("user.dir") + "/Reports/" + timeStamp + ".html";
		htmlReporter = new ExtentHtmlReporter(extentReportPath);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		// Set our document title, theme etc..
		htmlReporter.config().setDocumentTitle("Rentalhomes");
		htmlReporter.config().setReportName("Rentalhomes Production Testing");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
	}

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", readConfig.getChromeDriverPath());
		driver = new ChromeDriver();
		baseurl = readConfig.getBaseUrl();
		logger.info("Getting the base URL from Config File");
		driver.get(baseurl);
		logger.info("Opening the base URL");
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void tearDown(ITestResult tr) {
		if (tr.getStatus() == ITestResult.FAILURE) {
			captureScreenshot(driver, tr.getName());
			test.fail("Test Case failed!");
		}
		extent.flush();
		driver.close();
	}

	public static void captureScreenshot(WebDriver driver, String tname) {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			File target = new File(
					System.getProperty("user.dir") + "\\Screenshots\\" + tname + "-" + timestamp + ".png");
			FileUtils.copyFile(source, target);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

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
