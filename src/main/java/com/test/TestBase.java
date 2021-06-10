package com.test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestBase {
    private WebDriver driver;
    protected RemoteWebDriver remoteWebDriver;
    protected final Logger logger = LogManager.getLogger(getClass());

    public WebDriver getDriver() {
        return driver;
    }


    protected void createChromeDriver(URL hubURL, String siteURL) throws MalformedURLException {
        logger.info("create() - create selenium webdriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("browserVersion", "90");
        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4445/hub"), chromeOptions);
        driver.get(siteURL);
        driver = remoteWebDriver;
    }


    public void quit() {
        try {
            if (remoteWebDriver != null) {
                logger.info("quit() - Quit WebDriver SessionID[{}] and close every associated browser window", remoteWebDriver.getSessionId());
                remoteWebDriver.quit();
            }
        } catch (Exception e) {
            logger.warn("quit() - Encounter issue when quit web driver", e);
        }
    }


}
