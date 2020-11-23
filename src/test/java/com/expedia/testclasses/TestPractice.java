package com.expedia.testclasses;

import org.testng.annotations.Test;

import com.expedia.utilities.JSExecutor;

public class TestPractice extends BaseClass{
	@Test
	public void test() throws InterruptedException {
		JSExecutor.getJSExecutor(driver);
		System.out.println(JSExecutor.getTitle());
		System.out.println(JSExecutor.getScrollHeight());
		JSExecutor.scrollToBottomOfPage();
	}
}
