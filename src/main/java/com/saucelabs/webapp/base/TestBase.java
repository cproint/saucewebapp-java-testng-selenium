package com.saucelabs.webapp.base;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.UnexpectedException;
import java.util.Properties;

//import com.saucelabs.ci.sauceconnect.*;
//import com.saucelabs.ci.sauceconnect.AbstractSauceTunnelManager.SauceConnectException;
import com.saucelabs.saucerest.SauceREST;

/**
 * Simple TestNG config which demonstrates being instantiated via a DataProvider
 * in order to supply multiple browser combinations.
 */
public class TestBase {

	public String buildTag = System.getenv("BUILD_TAG");
	public String sauceUserName = System.getenv("SAUCE_USERNAME");
	public String sauceAccessKey = System.getenv("SAUCE_ACCESS_KEY");
	public String appURL;
	public static Properties prop;


	SauceREST sauce = new SauceREST(sauceUserName, sauceAccessKey);
	
	public TestBase() {

		prop = new Properties();
		try {
			FileInputStream configFile = new FileInputStream (System.getProperty("user.dir") + "/src/main/java/com/saucelabs/webapp/config/config.properties");
			prop.load(configFile);
			String log4jConfPath = System.getProperty("user.dir")+"/src/main/resources/log4j.properties";
			PropertyConfigurator.configure(log4jConfPath);
			
			appURL = prop.getProperty("appURL");

	
		} catch (FileNotFoundException e) {
	
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ThreadLocal variable which contains the {@link WebDriver} instance which is
	 * used to perform browser interactions with.
	 */
	private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

	/**
	 * ThreadLocal variable which contains the Sauce Job Id.
	 */
	private ThreadLocal<String> sessionId = new ThreadLocal<String>();

	/**
	 * DataProvider that explicitly sets the browser combinations to be used.
	 *
	 * @param testMethod
	 * @return Two dimensional array of objects with browser, version, and platform
	 *         information
	 */

	@DataProvider(name = "hardCodedBrowsers", parallel = true)
	public static Object[][] sauceBrowserDataProvider(Method testMethod) {
		return new Object[][] { 
            new Object[]{"chrome", "73.0", "macOS 10.14"},
            new Object[]{"firefox", "69.0", "Windows 10"},
            new Object[]{"MicrosoftEdge", "14.14393", "Windows 10"},
            new Object[]{"internet explorer", "11.285", "Windows 10"},
/*            new Object[]{"chrome", "73.0", "macOS 10.14"},
            new Object[]{"firefox", "69.0", "Windows 10"},
            new Object[]{"MicrosoftEdge", "14.14393", "Windows 10"},
            new Object[]{"internet explorer", "11.285", "Windows 10"},*/
            //new Object[]{"safari", "12.0", "macOS 10.14"}
		};
	}
	
	/**
	 * @return the {@link WebDriver} for the current thread
	 */
	public WebDriver getWebDriver() {
		return webDriver.get();
	}
	
/*	public String appURL() {	
		return prop.getProperty("appURL");
	}
	
	public String getAppUserName() {	
		return prop.getProperty("username");
	}
	
	public String getAppPassword() {	
		return prop.getProperty("password");
	}*/

	/**
	 *
	 * @return the Sauce Job id for the current thread
	 */
	public String getSessionId() {
		return sessionId.get();
	}

	/**
	 *
	 * set Sauce Labs related + W3C capabilities 
	 */
	private void sauceW3CCapabilities(MutableCapabilities baseCaps, String methodName, String tagName) {
		
		MutableCapabilities sauceCaps = new MutableCapabilities();
		
		sauceCaps.setCapability("username", sauceUserName);
		sauceCaps.setCapability("accessKey", sauceAccessKey);
		sauceCaps.setCapability("seleniumVersion", "3.141.59");
		sauceCaps.setCapability("name", methodName);
		sauceCaps.setCapability("tags", tagName);
		if (buildTag != null) {
			sauceCaps.setCapability("build", buildTag);
		}
		
	    //Assign the Sauce Options to the VM browser capabilities
        baseCaps.setCapability("sauce:options", sauceCaps);
		
		try {
			webDriver.set(new RemoteWebDriver(new URL("https://ondemand.saucelabs.com/wd/hub"), baseCaps));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		//set session ID		
		String id = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
		sessionId.set(id);
		
		// Reporting between Jenkins & Sauce Labs using Jenkins Plug-in - Print sessionID on console
		String message = String.format("SauceOnDemandSessionID=%1$s job-name=%2$s",
				(((RemoteWebDriver) getWebDriver()).getSessionId().toString()), methodName.getClass().getName());
		System.out.println(message);
		
	}
	
	/**
	 *
	 * Create Remote WebDriver with Base + W3C capabilities 
	 */
	protected void createDriver(String browser, String browserVersion, String platformName, String methodName, String tagName)
			throws MalformedURLException, UnexpectedException {

		MutableCapabilities baseCaps = new MutableCapabilities();

        if (browser.equals("chrome")) {
            baseCaps = new ChromeOptions().setExperimentalOption("w3c", true);
        }
        else if (browser.equals("firefox")) {
        	baseCaps = new FirefoxOptions();
        }
        else if (browser.equals("MicrosoftEdge")) {
        	baseCaps = new EdgeOptions();            
        }
        else if (browser.equals("internet explorer")) {
        	baseCaps = new InternetExplorerOptions();            
        }
        else if (browser.equals("safari")) {
        	baseCaps = new SafariOptions();
        }

        baseCaps.setCapability("browserVersion", browserVersion);
        baseCaps.setCapability("platformName", platformName);
		
		sauceW3CCapabilities(baseCaps, methodName, tagName);
	}

	/**
	 * Method that gets invoked after each testcase. Dumps browser log and Closes the browser
	 */
	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		// ((JavascriptExecutor) webDriver.get()).executeScript("sauce:job-result=" +
		// (result.isSuccess() ? "passed" : "failed"));
		try {
			if (result.isSuccess()) {
	
				sauce.jobPassed(((RemoteWebDriver) getWebDriver()).getSessionId().toString());
	
			} else {
	
				sauce.jobFailed(((RemoteWebDriver) getWebDriver()).getSessionId().toString());
			}
		}finally {
			
			getWebDriver().quit();
			webDriver.remove();
		}
	}
	/**
	 * Method that prints log statements on Sauce Labs command history
	 */
	protected void annotate(String text) {
		((JavascriptExecutor) webDriver.get()).executeScript("sauce:context=" + text);
	}

}
