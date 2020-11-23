package com.expedia.listeners;

import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

public class CustomReporter implements IReporter{

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		
		for(ISuite suiteRunner : suites) {
			
			Map<String,ISuiteResult> suiteResults = suiteRunner.getResults();
			
			for(ISuiteResult results:suiteResults.values()) {
				ITestContext context = results.getTestContext();
				
				System.out.println("Number of Passed Tests:"+context.getPassedTests().getAllResults().size());
				System.out.println("Number of failed Tests:"+context.getFailedTests().getAllResults().size());
			}
		}
		
	}

}
