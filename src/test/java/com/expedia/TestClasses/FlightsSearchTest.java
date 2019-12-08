package com.expedia.TestClasses;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.expedia.pageObjects.FlightsSearchPagePOM;
import com.expedia.utilities.JiraPolicy;
import com.expedia.utilities.XLUtility;

public class FlightsSearchTest extends BaseClass {
	private static FlightsSearchPagePOM fsp;

	@JiraPolicy(logTicketReady=false)
	@Test
	public void Basic_Test_001() throws InterruptedException {
		
		fsp = new FlightsSearchPagePOM(driver);
		fsp.clickFlightsTab();
		String currenturl = driver.getCurrentUrl();
		logger.info("Clicked on the flights tab");
		fsp.clickReturnRadioButton();
		logger.info("clicked Round trip radio button");
		fsp.setOriginCity("Mangalore");
		logger.info("Entered Origin City");
		fsp.setDestinationCity("Mumbai");
		logger.info("Entered Destination City");
		fsp.setDepartureDate("01/01/2020");
		logger.info("Entered Departure date");
		fsp.setReturnDate("01/15/2020");
		logger.info("Entered return Date");
		fsp.clickSearchFlights();
		logger.info("Clicked on Search Flights button");
		Thread.sleep(2000);
		String nexturl = driver.getCurrentUrl();
		if (!currenturl.equals(nexturl)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	@Test(dataProvider = "Cities") // Using the data provider method for test
									// data
	public void Basic_Test_002(String origingCity, String destinationCity,String depDate, String returnDate) throws InterruptedException {

		fsp = new FlightsSearchPagePOM(driver);
		fsp.clickFlightsTab();
		String currenturl = driver.getCurrentUrl();
		logger.info("Clicked on the flights tab");
		fsp.clickReturnRadioButton();
		logger.info("clicked Round trip radio button");
		fsp.setOriginCity(origingCity);
		logger.info("Entered Origin City");
		fsp.setDestinationCity(destinationCity);
		logger.info("Entered Destination City");
		fsp.setDepartureDate(depDate);
		logger.info("Entered Departure date");
		fsp.setReturnDate(returnDate);
		logger.info("Entered return Date");
		fsp.clickSearchFlights();
		logger.info("Clicked on Search Flights button");
		Thread.sleep(2000);
		String nexturl = driver.getCurrentUrl();
		if (!currenturl.equals(nexturl)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	/*
	 * Data Provider Method reading the test data from xlsx file.
	 */
	@DataProvider(name = "Cities")
	String[][] getData() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/expedia/testdata/TestData.xlsx";
		int rowcount = XLUtility.getRowCount(path, "Sheet1");
		int colcount = XLUtility.getCellCount(path, "Sheet1", 0);
		String[][] data = new String[rowcount][colcount];
		for (int i = 0; i < rowcount; i++) {//
			for (int j = 0; j < colcount; j++) {
				data[i][j] = XLUtility.getCellValue(path, "Sheet1", i, j);
			}
		}
		return data;
	}
}
