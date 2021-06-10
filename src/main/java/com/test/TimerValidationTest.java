package com.test;

import com.test.pages.CountdownPage;
import com.test.pages.PageLoadClass;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class TimerValidationTest extends TestBase {
    private PageLoadClass pageLoadClass;
    private CountdownPage countdownPage;

    @BeforeSuite
    public void beforeSuite() throws Exception {
        logger.info("Starting Suite.");
    }

    @AfterSuite
    public void afterSuite() {
        logger.info("afterSuite() - StoppingSuite.");
    }

   @BeforeMethod
   public void createDriver() throws MalformedURLException {

       URL hubURL = new URL("https://localhost:4445/hub");
       logger.info("tearDown() - AfterMethod - Quit WebDriver");
       createChromeDriver(hubURL, "https://e.ggtimer.com/");
       logger.info("AfterMethod cleanupWebDriver() - afterMethod - ciqSeleniumWebDriver.quit(isPassed).");

   }

    @AfterMethod
    public void cleanupWebDriver(ITestResult result) {
        logger.info("tearDown() - AfterMethod - Quit WebDriver");
        quit();
        logger.info("AfterMethod cleanupWebDriver() - afterMethod - .");
    }

    @Test(description = "Test Timer")
    public void testTimer() throws Exception {
        pageLoadClass = new PageLoadClass(getDriver());
        Assert.assertEquals(pageLoadClass.getPageName(),"e.gg Timer - a simple countdown timer","Title not match");
        pageLoadClass.isElementToEnterCountVisbile();
        pageLoadClass.enterCount("25");
        pageLoadClass.clickOnStart();
        logger.info("Starting timer for 25 sec..");
        countdownPage = new CountdownPage(getDriver());
        countdownPage.checkCountdown();
        logger.info("Completed Validation");

    }
}
