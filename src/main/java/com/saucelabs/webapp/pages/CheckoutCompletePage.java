package com.saucelabs.webapp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucelabs.webapp.base.TestBase;

/**
 * @author mtulugu
 *
 */
public class CheckoutCompletePage extends TestBase {

	public WebDriver driver;
	public static String url = "https://www.saucedemo.com";

	@FindBy(css = ".bm-burger-button > button")
	@CacheLookup
	private WebElement openMenuButton;

	@FindBy(linkText = "Logout")
	@CacheLookup
	private WebElement logoutLink;

	public CheckoutCompletePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Actions

	public boolean isLogoutLinkDisplayed() {

		return logoutLink.isDisplayed();
	}

	public LoginPage clickLogoutLink() {
		openMenuButton.click();
		logoutLink.click();
		return new LoginPage(driver);
	}

}
