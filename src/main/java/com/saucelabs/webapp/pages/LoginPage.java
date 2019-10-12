package com.saucelabs.webapp.pages;

import org.openqa.selenium.JavascriptExecutor;
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
public class LoginPage extends TestBase{

	public WebDriver driver;
	public String url = "https://www.saucedemo.com";

	@FindBy(css = ".login_logo")
	@CacheLookup
	private WebElement swaglabsLogo;

	@FindBy(id = "user-name")
	@CacheLookup
	private WebElement username;

	@FindBy(id = "password")
	@CacheLookup
	private WebElement password;

	@FindBy(xpath = "//input[@value='LOGIN']")
	@CacheLookup
	private WebElement loginButton;

	@FindBy(xpath = "//h3[@data-test='error']")
	@CacheLookup
	private WebElement invalidCredentialsWarningMessage;

	public static LoginPage loginPage(WebDriver driver) {
		LoginPage page = new LoginPage(driver);
		page.getURL();
		return page;
	}

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void getURL() {
		this.driver.get(url);
	}

	// Actions

	public String getPageTitle() {

		return driver.getTitle();
	}

	public boolean isSwaglabsLogoDisplayed() {

		return swaglabsLogo.isDisplayed();
	}

	public boolean isUserNameTextBoxDisplayed() {

		return username.isDisplayed();
	}

	public boolean isUserNameTextBoxEnabled() {

		return username.isEnabled();
	}

	public boolean isUserNameTextBoxEmpty() {

		return username.getText().isEmpty();
	}

	public boolean isPasswordTextBoxDisplayed() {

		return password.isDisplayed();
	}

	public boolean isPasswordTextBoxEnabled() {

		return password.isEnabled();
	}

	public boolean isPasswordTextBoxEmpty() {

		return password.getText().isEmpty();
	}

	public boolean isLoginButtonDisplayed() {

		return loginButton.isDisplayed();
	}

	public boolean isLoginButtonEnabled() {

		return loginButton.isEnabled();
	}

	public String getLoginButtonText() {

		return loginButton.getAttribute("value");
	}

	public boolean invalidCredentialsWarningMessageDisplayed() {

		return invalidCredentialsWarningMessage.isDisplayed();
	}

	public String getInvalidCredentialsWarningMessage() {

		return invalidCredentialsWarningMessage.getText();
	}

	/**
	 * @param email
	 * @param pwd
	 * @return
	 */

	public ProductsPage login(String userName, String pwd) {
		username.sendKeys(userName);
		password.sendKeys(pwd);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", loginButton);
		return new ProductsPage(driver);
	}

}
