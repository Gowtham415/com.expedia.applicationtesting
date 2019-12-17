package com.expedia.utilities;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.expedia.listeners.JiraServiceProvider;

public class ExpediaListener implements ITestListener{

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		JiraPolicy jiraPolicy = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(JiraPolicy.class);
		boolean isJiraPolicyEnabled = jiraPolicy.logTicketReady();
		if(isJiraPolicyEnabled){
			JiraServiceProvider newJSP= new JiraServiceProvider("", "gowthampage@gmail.com", "10je1A0415", "EX board");
			String issueDescription = result.getMethod().getConstructorOrMethod().getMethod().getName()+ " Failed due to Assertion failures or Exceptions";
			String issueSummary = result.getThrowable().getMessage()+"\n";
			issueSummary.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));
			
			newJSP.createJiraTicket("Bug", issueSummary, issueDescription, "Gowtham");
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		System.out.println("Test Passed");
		
	}

}
