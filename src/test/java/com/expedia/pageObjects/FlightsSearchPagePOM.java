package com.expedia.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.expedia.utilities.ExplicitWaitsUtility;

public class FlightsSearchPagePOM extends BasePage{
	
	public FlightsSearchPagePOM(WebDriver driver) {
		super(driver);
	}
	
	By flightsTab= By.id("tab-flight-tab-hp");
	By returnRadioButton= By.id("flight-type-roundtrip-label-hp-flight");
	By onewayRadioButton= By.id("flight-type-one-way-label-hp-flight");
	By mulicityRadioButton= By.id("flight-type-multi-dest-label-hp-flight");
	By originCity= By.id("flight-origin-hp-flight");
	By destinationCity= By.id("flight-destination-hp-flight");
	By departureDate= By.id("flight-departing-hp-flight");
	By returnDate= By.id("flight-returning-hp-flight");
	By searchFlights= By.xpath("//section[@id='section-flight-tab-hp']//button[@type='submit']");
	
	public void clickFlightsTab(){
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
		driverWait.waitUntilElementisVisible(originCity).click();//To give additional time for driver to send keys after element is found
		driverWait.waitUntilElementisVisible(originCity).sendKeys(city);
	}

	public void setDestinationCity(String city) {
		driverWait.waitUntilElementisVisible(destinationCity).click();//To give additional time for driver to send keys after element is found
		driverWait.waitUntilElementisVisible(destinationCity).sendKeys(city);
	}

	public void setDepartureDate(String date) {
		driverWait.waitUntilElementisVisible(departureDate).clear();
		driverWait.waitUntilElementisVisible(departureDate).sendKeys(date);
	}

	public void setReturnDate(String date) {
		driverWait.waitUntilElementisVisible(returnDate).sendKeys(Keys.CONTROL+"a");
		driverWait.waitUntilElementisVisible(returnDate).sendKeys(Keys.DELETE);
		driverWait.waitUntilElementisVisible(returnDate).sendKeys(date);
	}

	public void clickSearchFlights() {
		driverWait.waitUntilElementisClickable(searchFlights).click();
	}
	
	
	public void searchForFlights_Return(String originCity,String destinationCity,String departureTime,String returnDate) {
		clickFlightsTab();
		clickReturnRadioButton();
		setOriginCity(originCity);
		setDestinationCity(destinationCity);
		setDepartureDate(departureTime);
		setReturnDate(returnDate);
		clickSearchFlights();
	}
	
	public void searchForFlights_oneWay(String originCity,String destinationCity,String departureTime,String returnDate) {
		clickFlightsTab();
		clickOnewayRadioButton();
		setOriginCity(originCity);
		setDestinationCity(destinationCity);
		setDepartureDate(departureTime);
		clickSearchFlights();
	}
}
