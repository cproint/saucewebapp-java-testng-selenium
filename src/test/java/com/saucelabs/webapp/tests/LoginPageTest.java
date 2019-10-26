package com.saucelabs.webapp.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import org.testng.annotations.Test;

import com.saucelabs.webapp.base.TestBase;
import com.saucelabs.webapp.pages.LoginPage;

/**
 * @author mtulugu
 *
 */
public class LoginPageTest extends TestBase {

	@Test(dataProvider = "hardCodedBrowsers", enabled = true)
	public void verifyLoginPageTitle(String browser, String version, String os, Method method)
			throws MalformedURLException, InvalidElementStateException, UnexpectedException {

		createDriver(browser, version, os, method.getName(), "LoginPageTests");
		WebDriver driver = getWebDriver();

		annotate("Open SauceDemo App URL...");
		LoginPage page = LoginPage.loginPage(driver);

		annotate("Asserting SauceDemo App Page Title...");
		Assert.assertEquals(page.getPageTitle(), "Swag Labs");
	}

	@Test(dataProvider = "hardCodedBrowsers", enabled = true)
	public void verifyUserNameTextBoxIsDisplayed(String browser, String version, String os, Method method)
			throws MalformedURLException, InvalidElementStateException, UnexpectedException {

		createDriver(browser, version, os, method.getName(), "LoginPage_Tests");
		WebDriver driver = getWebDriver();

		annotate("Open SauceDemo App URL...");
		LoginPage page = LoginPage.loginPage(driver);

		annotate("Asserting wheather UserName TextBox is Displayed or not");
		assertTrue(page.isUserNameTextBoxDisplayed());
	}

	@Test(dataProvider = "hardCodedBrowsers", enabled = true)
	public void verifyUserNameTextBoxIsEnabled(String browser, String version, String os, Method method)
			throws MalformedURLException, InvalidElementStateException, UnexpectedException {

		createDriver(browser, version, os, method.getName(), "LoginPage_Tests");
		WebDriver driver = getWebDriver();

		annotate("Open SauceDemo App URL...");
		LoginPage page = LoginPage.loginPage(driver);

		annotate("Asserting wheather UserName TextBox is Enabled or not");
		assertTrue(page.isUserNameTextBoxEnabled());
	}

	@Test(dataProvider = "hardCodedBrowsers", enabled = true)
	public void verifyUserNameTextIsBoxEmpty(String browser, String version, String os, Method method)
			throws MalformedURLException, InvalidElementStateException, UnexpectedException {

		createDriver(browser, version, os, method.getName(), "LoginPage_Tests");
		WebDriver driver = getWebDriver();

		annotate("Open SauceDemo App URL...");
		LoginPage page = LoginPage.loginPage(driver);

		annotate("Asserting wheather UserName TextBox is Empty or not");
		assertTrue(page.isUserNameTextBoxEmpty());
	}

	@Test(dataProvider = "hardCodedBrowsers", enabled = true)
	public void verifyPasswordTextBoxIsDisplayed(String browser, String version, String os, Method method)
			throws MalformedURLException, InvalidElementStateException, UnexpectedException {

		createDriver(browser, version, os, method.getName(), "LoginPage_Tests");
		WebDriver driver = getWebDriver();

		annotate("Open SauceDemo App URL...");
		LoginPage page = LoginPage.loginPage(driver);

		annotate("Asserting wheather Password TextBox is Displayed or not");
		assertTrue(page.isPasswordTextBoxDisplayed());
	}

	@Test(dataProvider = "hardCodedBrowsers", enabled = true)
	public void verifyPasswordTextBoxIsEnbled(String browser, String version, String os, Method method)
			throws MalformedURLException, InvalidElementStateException, UnexpectedException {

		createDriver(browser, version, os, method.getName(), "LoginPage_Tests");
		WebDriver driver = getWebDriver();

		annotate("Open SauceDemo App URL...");
		LoginPage page = LoginPage.loginPage(driver);

		annotate("Asserting wheather Password TextBox is Enabled or not");
		assertTrue(page.isPasswordTextBoxEnabled());
	}

	@Test(dataProvider = "hardCodedBrowsers", enabled = true)
	public void verifyPasswordTextBoxIsEmpty(String browser, String version, String os, Method method)
			throws MalformedURLException, InvalidElementStateException, UnexpectedException {

		createDriver(browser, version, os, method.getName(), "LoginPage_Tests");
		WebDriver driver = getWebDriver();

		annotate("Open SauceDemo App URL...");
		LoginPage page = LoginPage.loginPage(driver);

		annotate("Asserting wheather Password TextBox is Empty or not");
		assertTrue(page.isPasswordTextBoxEmpty());
	}

	@Test(dataProvider = "hardCodedBrowsers", enabled = true)
	public void verifyLoginButtonIsDisplayed(String browser, String version, String os, Method method)
			throws MalformedURLException, InvalidElementStateException, UnexpectedException {

		createDriver(browser, version, os, method.getName(), "LoginPage_Tests");
		WebDriver driver = getWebDriver();

		annotate("Open SauceDemo App URL...");
		LoginPage page = LoginPage.loginPage(driver);

		annotate("Asserting wheather Login Button is Displayed or not");
		assertTrue(page.isLoginButtonDisplayed());
	}

	@Test(dataProvider = "hardCodedBrowsers", enabled = true)
	public void verifyLoginButtonIsEnabled(String browser, String version, String os, Method method)
			throws MalformedURLException, InvalidElementStateException, UnexpectedException {

		createDriver(browser, version, os, method.getName(), "LoginPageTests");
		WebDriver driver = getWebDriver();

		annotate("Open SauceDemo App URL...");
		LoginPage page = LoginPage.loginPage(driver);

		annotate("Asserting Swaglabs Demo App Page Title...");
		assertTrue(page.isLoginButtonEnabled());
	}

	@Test(dataProvider = "hardCodedBrowsers", enabled = true)
	public void verifyLoginButton(String browser, String version, String os, Method method)
			throws MalformedURLException, InvalidElementStateException, UnexpectedException {

		createDriver(browser, version, os, method.getName(), "LoginPage_Tests");
		WebDriver driver = getWebDriver();

		annotate("Open SauceDemo App URL...");
		LoginPage page = LoginPage.loginPage(driver);

		annotate("Asserting Swaglabs Demo App Page Title...");

		assertEquals(page.getLoginButtonText(), "LOGIN");
	}

}
