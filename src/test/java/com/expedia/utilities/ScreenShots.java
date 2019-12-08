package com.expedia.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShots {
	
	public static String captureScreenshot(WebDriver driver,String tname) {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			String destFilePath=System.getProperty("user.dir") + "\\Screenshots\\" + tname + "-" + timestamp + ".png";
			File target = new File(destFilePath);
			FileUtils.copyFile(source, target);
			return destFilePath;
		} catch (Exception e) {
			return "Screen Not available";
		}
	}
}
