package com.saucelabs.webapp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author mtulugu
 *
 */
public class CheckoutInformationPage {

	public WebDriver driver;
	public static String url = "https://www.saucedemo.com";

	@FindBy(css = "input#first-name")
	@CacheLookup
	private WebElement firstName;

	@FindBy(css = "input#last-name")
	@CacheLookup
	private WebElement lastName;

	@FindBy(css = "input#postal-code")
	@CacheLookup
	private WebElement zipCode;

	@FindBy(css = ".btn_primary.cart_button")
	@CacheLookup
	private WebElement continueButton;

	public CheckoutInformationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Actions

	public boolean isFirstNameTestBoxDisplayed() {

		return firstName.isDisplayed();
	}

	public CheckoutOverviewPage clickContinueButton() {

		firstName.sendKeys("Murali");
		lastName.sendKeys("Tulugu");
		zipCode.sendKeys("94107");

		continueButton.click();
		return new CheckoutOverviewPage(driver);
	}

}
