package com.test.pages;

import com.paulhammant.ngwebdriver.NgWebDriver;
import com.test.utils.PageObjectsException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected final WebDriver webDriver;
    protected final NgWebDriver ngWebDriver;
    protected final FluentWait<WebDriver> fluentWait;
    protected final Logger logger = LogManager.getLogger(getClass());
    // Selenium timeout configurations
    public static final Duration WAIT_ELEMENT_TIMEOUT = Duration.ofSeconds(35);
    public static final Duration WAIT_ELEMENT_POLLING = Duration.ofSeconds(2);
    public static final Duration WAIT_PAGE_TIMEOUT = Duration.ofSeconds(60);


    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.ngWebDriver = new NgWebDriver((JavascriptExecutor) webDriver).withRootSelector("body");
        fluentWait = new FluentWait<>(webDriver).withTimeout(WAIT_ELEMENT_TIMEOUT).pollingEvery(WAIT_ELEMENT_POLLING)
                .ignoring(NoSuchElementException.class);
        PageFactory.initElements(webDriver, this);
    }

    protected void waitForElementToAppear(WebElement element) {
        fluentWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeClickable(WebElement webElement) {
        fluentWait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected String getText(WebElement element) {
        waitForElementToAppear(element);
        return element.getText();
    }

    public void waitForPageToLoad() throws TimeoutException{
        fluentWait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
                .toString().equals("complete"));

        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };

        fluentWait.until(expectation);

    }

    protected List<WebElement> getElements(By by) {
        return webDriver.findElements(by);
    }

    protected boolean doesElementExist(By by) {
        return !getElements(by).isEmpty();
    }

    protected String getWebElementText(By locator) {
        List<WebElement> elements = getElements(locator);
        return elements.isEmpty() ? null : elements.get(0).getText();
    }


}
