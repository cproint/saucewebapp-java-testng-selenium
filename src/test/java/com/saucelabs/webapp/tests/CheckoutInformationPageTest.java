package com.saucelabs.webapp.tests;

import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.saucelabs.webapp.base.TestBase;
import com.saucelabs.webapp.pages.ProductsPage;
import com.saucelabs.webapp.pages.ShoppingCartPage;
import com.saucelabs.webapp.pages.CheckoutInformationPage;
import com.saucelabs.webapp.pages.LoginPage;

/**
 * @author mtulugu
 *
 */

public class CheckoutInformationPageTest extends TestBase {

	LoginPage loginPage;
	ProductsPage productsPage;
	ShoppingCartPage shoppingCartPage;
	CheckoutInformationPage checkoutInformationPage;

	@Test(dataProvider = "hardCodedBrowsers", enabled = true)
	public void verifyProductLabelDisplayed(String browser, String version, String os, Method method)
			throws MalformedURLException, InvalidElementStateException, UnexpectedException {

		createDriver(browser, version, os, method.getName(), "ShoppingCartPageTests");
		WebDriver driver = getWebDriver();

		annotate("Open SauceDemo URL...");
		LoginPage page = LoginPage.loginPage(driver);

		annotate("Login to SauceDemo App...");
		productsPage = page.login("standard_user", "secret_sauce");

		shoppingCartPage = productsPage.clickShoppingCartIcon();
		checkoutInformationPage = shoppingCartPage.clickCheckoutButton();
		assertTrue(checkoutInformationPage.isFirstNameTestBoxDisplayed());

	}

}
