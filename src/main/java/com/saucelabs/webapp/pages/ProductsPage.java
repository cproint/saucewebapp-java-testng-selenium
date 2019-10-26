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
public class ProductsPage extends TestBase {

	public WebDriver driver;

	@FindBy(css = ".product_sort_container")
	@CacheLookup
	private WebElement productSortContainer;

	@FindBy(css = ".product_label")
	@CacheLookup
	private WebElement productLabel;

	@FindBy(css = "div.peek")
	@CacheLookup
	private WebElement sauceBotImage;

	@FindBy(css = "path")
	@CacheLookup
	private WebElement shoppingCartIcon;

	@FindBy(className = "btn_primary")
	@CacheLookup
	private WebElement addToCartButton;

	public ProductsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Actions

	public boolean isProductSortContainerDisplayed() {

		return productSortContainer.isDisplayed();
	}

	public ShoppingCartPage clickShoppingCartIcon() {

		addToCartButton.click();
		shoppingCartIcon.click();

		return new ShoppingCartPage(driver);
	}

}