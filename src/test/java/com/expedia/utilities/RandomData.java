package com.expedia.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomData {

	public static String randomAlphabeticString(int length) {
		String randomString = RandomStringUtils.randomAlphabetic(length);
		return randomString;
	}

	public static String randomNumberString(int length) {
		String randomString = RandomStringUtils.randomNumeric(length);
		return randomString;
	}
}
