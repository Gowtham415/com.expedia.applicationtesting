package com.expedia.TestClasses;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.expedia.pageObjects.FlightsSearchPagePOM;
import com.expedia.utilities.JiraPolicy;
import com.expedia.utilities.XLUtility;

public class FlightsSearchTest extends BaseClass {
	
	
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

	@JiraPolicy(logTicketReady=false)
	@Test
	public void Basic_Test_001() throws InterruptedException {
		String basePage = driver.getCurrentUrl();
		page.getInstance(FlightsSearchPagePOM.class).searchForFlights_Return("Hyderabad", "Bengaluru", "01/01/2020", "10/01/2020");
		String nxtPage= driver.getCurrentUrl();
		if(basePage.equals(nxtPage)) {
			Assert.assertTrue(true);
		}else {
			Assert.assertFalse(false);
		}
	}

	@Test(dataProvider = "Cities") // Using the data provider method for test
									// data
	public void Basic_Test_002(String originCity, String destinationCity,String departureTime, String returnDate) throws InterruptedException {
		String basePage = driver.getCurrentUrl();
		page.getInstance(FlightsSearchPagePOM.class).searchForFlights_Return(originCity, destinationCity, departureTime, returnDate);
		String nxtPage= driver.getCurrentUrl();
		if(basePage.equals(nxtPage)) {
			Assert.assertTrue(true);
		}else {
			Assert.assertFalse(false);
		}
		
	}

	
}
