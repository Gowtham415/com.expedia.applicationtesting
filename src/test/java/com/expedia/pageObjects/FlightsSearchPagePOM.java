package com.expedia.pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.expedia.utilities.ExplicitWaitsUtility;

public class FlightsSearchPagePOM {
	
	public WebDriver driver;
	
	private static ExplicitWaitsUtility wait; 	
	
	@FindBy(id="tab-flight-tab-hp")
	WebElement flightsTab;
	
	@FindBy(id="flight-type-roundtrip-label-hp-flight")
	WebElement returnRadioButton;
	
	@FindBy(id="flight-type-one-way-label-hp-flight")
	WebElement onewayRadioButton;
	
	@FindBy(id="flight-type-multi-dest-label-hp-flight")
	WebElement mulicityRadioButton;
	
	@FindBy(id="flight-origin-hp-flight")
	WebElement originCity;
	
	@FindBy(id="flight-destination-hp-flight")
	WebElement destinationCity;
	
	@FindBy(id="flight-departing-hp-flight")
	WebElement departureDate;
	
	@FindBy(id="flight-returning-hp-flight")
	WebElement returnDate;
	
	@FindBy(xpath="//section[@id='section-flight-tab-hp']//button[@type='submit']")
	WebElement searchFlights;
	
	public FlightsSearchPagePOM(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait = new ExplicitWaitsUtility(driver);
	}
	
	public void clickFlightsTab(){
		wait.waitUntilElementisClickable(flightsTab).click();
	}

	public void clickReturnRadioButton() {
		wait.waitUntilElementisVisible(returnRadioButton).click();
	}

	public void clickOnewayRadioButton() {
		wait.waitUntilElementisVisible(onewayRadioButton).click();
	}

	public void clickMulicityRadioButton() {
		wait.waitUntilElementisVisible(mulicityRadioButton).click();
	}

	public void setOriginCity(String city) {
		wait.waitUntilElementisVisible(originCity).click();//To give additional time for driver to send keys after element is found
		wait.waitUntilElementisVisible(originCity).sendKeys(city);
	}

	public void setDestinationCity(String city) {
		wait.waitUntilElementisVisible(destinationCity).click();//To give additional time for driver to send keys after element is found
		wait.waitUntilElementisVisible(destinationCity).sendKeys(city);
	}

	public void setDepartureDate(String date) {
		wait.waitUntilElementisVisible(departureDate).clear();
		wait.waitUntilElementisVisible(departureDate).sendKeys(date);
	}

	public void setReturnDate(String date) {
		wait.waitUntilElementisVisible(returnDate).sendKeys(Keys.CONTROL+"a");
		wait.waitUntilElementisVisible(returnDate).sendKeys(Keys.DELETE);
		wait.waitUntilElementisVisible(returnDate).sendKeys(date);
	}

	public void clickSearchFlights() {
		wait.waitUntilElementisClickable(searchFlights).click();
	}
}
