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
public class CheckoutOverviewPage {

	public WebDriver driver;
	public static String url = "https://www.saucedemo.com";

	@FindBy(linkText = "FINISH")
	@CacheLookup
	private WebElement finishButton;

	public CheckoutOverviewPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Actions

	public boolean isFinishButtonDisplayed() {

		return finishButton.isDisplayed();
	}

	public CheckoutCompletePage clickFinishButton() {
		finishButton.click();
		return new CheckoutCompletePage(driver);
	}

}
