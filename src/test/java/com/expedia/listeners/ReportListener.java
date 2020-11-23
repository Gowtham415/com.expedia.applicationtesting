package com.expedia.listeners;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.expedia.testclasses.BaseClass;
import com.expedia.utilities.ScreenShots;

public class ReportListener implements ITestListener{

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public  ExtentTest test;
	
	
	@Override
	public void onStart(ITestContext context) {
		// For Extent reporting
				String timeStamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
				String extentReportPath = System.getProperty("user.dir") + "/Reports/" + timeStamp + ".html";
				htmlReporter = new ExtentHtmlReporter(extentReportPath);
				extent = new ExtentReports();
				extent.attachReporter(htmlReporter);
				// Set our document title, theme etc..
				htmlReporter.config().setDocumentTitle("Expedia Testing");
				htmlReporter.config().setReportName("Expedia Production Testing");
				htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
				htmlReporter.config().setTheme(Theme.DARK);		
	}
	
	@Override
	public void onTestStart(ITestResult result) {
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extent
			.createTest(result.getName())
			.assignAuthor("Gowtham")
			.pass("Test case Passed");
		
		extent.flush();
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			extent
				.createTest(result.getName())
				.assignAuthor("Gowtham")
				.addScreenCaptureFromPath(ScreenShots.captureScreenshot(BaseClass.driver, result.getName()))
				.log(Status.FAIL, result.getName()+" failed to execute!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		extent.flush();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extent
		.createTest(result.getName())
		.assignAuthor("Gowtham")
		.pass("Test case skipped");
	
	extent.flush();
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
	}

}
