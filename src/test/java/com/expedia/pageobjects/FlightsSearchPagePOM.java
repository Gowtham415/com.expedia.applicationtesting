package com.expedia.pageobjects;

import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.expedia.utilities.DatesUtility;

public class FlightsSearchPagePOM extends BasePage {

	public FlightsSearchPagePOM(WebDriver driver) {
		super(driver);
	}

	By flightsTab = By.id("tab-flight-tab-hp");
	By returnRadioButton = By.id("flight-type-roundtrip-label-hp-flight");
	By onewayRadioButton = By.id("flight-type-one-way-label-hp-flight");
	By mulicityRadioButton = By.id("flight-type-multi-dest-label-hp-flight");
	By originCity = By.id("flight-origin-hp-flight");
	By destinationCity = By.id("flight-destination-hp-flight");
	By departureDate = By.id("flight-departing-hp-flight");
	By returnDate = By.id("flight-returning-hp-flight");
	By searchFlights = By.xpath("//section[@id='section-flight-tab-hp']//button[@type='submit']");

	By departureDateForOneWay = By.xpath("//input[@id='flight-departing-single-hp-flight']");

	public void clickFlightsTab() {
		driverWait.waitUntilElementisClickable(flightsTab).click();
	}

	public void clickReturnRadioButton() {
		driverWait.waitUntilElementisVisible(returnRadioButton).click();
	}

	public void clickOnewayRadioButton() {
		driverWait.waitUntilElementisVisible(onewayRadioButton).click();
	}

	public void clickMulicityRadioButton() {
		driverWait.waitUntilElementisVisible(mulicityRadioButton).click();
	}

	public void setOriginCity(String city) {
		driverWait.waitUntilElementisVisible(originCity).click();
		driverWait.waitUntilElementisVisible(originCity).sendKeys(city);
	}

	public void setDestinationCity(String city) {
		driverWait.waitUntilElementisVisible(destinationCity).click();
		driverWait.waitUntilElementisVisible(destinationCity).sendKeys(city);
	}

	public void setDepartureDate(String date) {
		driverWait.waitUntilElementisVisible(departureDate).clear();
		driverWait.waitUntilElementisVisible(departureDate).sendKeys(date);
	}

	public void setReturnDate(String date) {
		driverWait.waitUntilElementisVisible(returnDate).sendKeys(Keys.CONTROL + "a");
		driverWait.waitUntilElementisVisible(returnDate).sendKeys(Keys.DELETE);
		driverWait.waitUntilElementisVisible(returnDate).sendKeys(date);
	}

	public void clickSearchFlights() {
		driverWait.waitUntilElementisClickable(searchFlights).click();
	}

	public void setDepartureDateOneWayFromCalendar(String departurdate) {
		try {
			driverWait.waitUntilElementisVisible(departureDateForOneWay).click();		
			String monthInFutureDate = departurdate.split("/")[1];
			By departureDateXpath;
			Calendar cal = Calendar.getInstance();
			if (monthInFutureDate.equals(Integer.toString(cal.get(Calendar.MONTH) + 1))) {
				int tempInt = Integer.parseInt(monthInFutureDate);
				departureDateXpath = By
						.xpath("//*[@id=\"flight-departing-wrapper-single-hp-flight\"]//div[2]//button[text()=' "
								+ tempInt + "']");
			} else {
				int tempInt = Integer.parseInt(monthInFutureDate);
				departureDateXpath = By
						.xpath("//*[@id=\"flight-departing-wrapper-single-hp-flight\"]//div[3]//button[text()=' "
								+ tempInt + "']");
			}
			driverWait.waitUntilElementisVisible(departureDateXpath).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchForReturnFlights(String originCity, String destinationCity, String departureTime,
			String returnDate) {
		clickFlightsTab();
		clickReturnRadioButton();
		setOriginCity(originCity);
		setDestinationCity(destinationCity);
		setDepartureDate(departureTime);
		setReturnDate(returnDate);
		clickSearchFlights();
	}

	public void searchForOneWayFlights(String originCity, String destinationCity, String departureTime,
			String returnDate) {
		clickFlightsTab();
		clickOnewayRadioButton();
		setOriginCity(originCity);
		setDestinationCity(destinationCity);
		setDepartureDate(departureTime);
		clickSearchFlights();
	}

}
