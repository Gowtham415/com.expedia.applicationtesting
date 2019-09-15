package com.expedia.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomData {

	public static String randomAlphabeticString() {
		String randomString = RandomStringUtils.randomAlphabetic(10);
		return randomString;
	}

	public static String randomNumberString() {
		String randomString = RandomStringUtils.randomNumeric(8);
		return randomString;
	}
}
