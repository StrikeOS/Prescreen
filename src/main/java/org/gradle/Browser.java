package org.gradle;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.safari.SafariDriver;

public class Browser {

	private static Browser browser;
	public static RemoteWebDriver driver;
	
	public static void quit() {
		try {
			driver.quit();
		}catch (UnreachableBrowserException eQuit){
			Thread.currentThread().interrupt();
			driver.quit();
		}
		driver=null;
	}
	
	public static void close() {
		driver.close();
	}
		
	public static Browser getBrowser() {
		return browser;
	}

	public static void setBrowser(Browser browser) {
		Browser.browser = browser;
	}

	public static void setDriver(RemoteWebDriver driver) {
		Browser.driver = driver;
	}

	public static RemoteWebDriver getDriver() {
		if (driver == null) {
			try {
				setBrowser(new Browser(Global.BROWSER));
			}catch(MalformedURLException e){}
			driver.manage().timeouts().pageLoadTimeout(Global.DEFAULT_IMPLICIT_WAIT, TimeUnit.SECONDS);
		}
		return driver;
	}
	
	public Browser() {	
	}
	
	public Browser(String browserType) throws MalformedURLException {
		Log.info("Creating an instance of a " + browserType + " browser.");
		switch (Global.TESTENV) {
		case Global.HOME:
			switch (browserType) {
			case Global.CHROME:
				System.setProperty("webdriver.chrome.driver", Global.CHROME_DRIVER_PATH);
				Browser.setDriver(new ChromeDriver());
				break;
			case Global.INTERNET_EXPLORER:
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				capabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
				System.setProperty("webdriver.ie.driver", Global.IE_DRIVER_PATH);
				Browser.setDriver(new InternetExplorerDriver(capabilities));
				break;
			case Global.OPERA:
				System.setProperty("webdriver.opera.driver", Global.OPERA_DRIVER_PATH);
				Browser.setDriver(new OperaDriver());
				break;
			case Global.SAFARI:
				Browser.setDriver(new SafariDriver());
				break;
			default:
				System.setProperty("webdriver.gecko.driver", Global.FIREFOX_DRIVER_PATH);
				DesiredCapabilities capabilities_fx = DesiredCapabilities.firefox();
			    //capabilities_fx.setCapability("marionette", true);
			    //capabilities_fx.setCapability("marionette.logging",false);
			    capabilities_fx.setBrowserName("firefox");
			    Browser.setDriver(new FirefoxDriver(capabilities_fx));
				break;
			}
			break;
		case Global.GRID:
			//Browser.setDriver(new RemoteWebDriver(new URL(Global.SELENIUM_GRID_HUB_URL), getBrowserCapabilities_GRID(browserType)));
			break;
		case Global.BROWSESTACK:
			//Browser.setDriver(new RemoteWebDriver(new URL(Global.SELENIUM_BROWSESTACK_HUB_URL), getBrowserCapabilities_BROWSESTACK(browserType)));
			break;
		default:
			break;
		}
	}
	
	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
