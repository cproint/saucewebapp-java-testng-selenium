package com.saucelabs.webapp.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author mtulugu
 *
 */
public class ShoppingCartPage {

	public WebDriver driver;
	public static String url = "https://www.saucedemo.com";

	@FindBy(linkText = "CHECKOUT")
	@CacheLookup
	private WebElement checkoutButton;

	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void getURL() {
		this.driver.get(url);
	}

	// Actions

	public boolean isCheckoutButtonDisplayed() {

		return checkoutButton.isDisplayed();
	}

	public CheckoutInformationPage clickCheckoutButton() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", checkoutButton);
		return new CheckoutInformationPage(driver);
	}

}
