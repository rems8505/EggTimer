package com.test.pages;

import com.test.utils.PageObjectsException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageLoadClass extends BasePage{

    public static final String TITLE_CLASS = "head meta[name='title']";
    public static final String INPUT_TEXT = "input[class=EggTimer-start-time-input-text]";
    public static final String START_BUTTON = "button[data-for=startpage]";

    @FindBy(css = TITLE_CLASS)
    private WebElement pageName;

    @FindBy(css = INPUT_TEXT)
    private WebElement inputTextWE;

    @FindBy(css = START_BUTTON)
    private WebElement startButtonWE;

    public PageLoadClass(WebDriver driver) {
        super(driver);
    }

    public String getPageName() {
        waitForElementToAppear(pageName);
        return getText(pageName);
    }

    public boolean isElementToEnterCountVisbile () {
        return doesElementExist(By.cssSelector(INPUT_TEXT));
    }

    public void enterCount(String value) throws PageObjectsException {
        if(doesElementExist(By.cssSelector(INPUT_TEXT))){
            inputTextWE.click();
            inputTextWE.sendKeys(value);
        }
        else{
            throw new PageObjectsException(INPUT_TEXT + "Element not Visible");
        }
    }
    public void clickOnStart() throws PageObjectsException {
        waitForElementToBeClickable(startButtonWE);
        if(doesElementExist(By.cssSelector(START_BUTTON))){
            startButtonWE.click();
        }
        else{
            throw new PageObjectsException(INPUT_TEXT + "Element not Visible");
        }
    }

}
