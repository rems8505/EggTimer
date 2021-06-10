package com.test.pages;

import com.test.utils.PageObjectsException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CountdownPage extends BasePage {

    public static final String COUNTER_CSS = "div[class=ClassicTimer-inner] [class=ClassicTimer-time]";
    @FindBy(css = COUNTER_CSS)
    private List<WebElement> counter_WE;

    public CountdownPage(WebDriver driver) {
        super(driver);
    }

    public void checkCountdown() throws PageObjectsException {
        int intValue = 0;
        int intPrvValue = 0;
        while(true){
            for(WebElement e : counter_WE){
                String value = e.getText();
                if(value.equals("Time Expired!")){
                    logger.info("Time Expired! , Exiting ..");
                    break;
                }
                else{
                    if(intValue==0){
                        intValue = Integer.parseInt(value);
                        intPrvValue = intValue;
                    }
                    else{
                        intValue = Integer.parseInt(value);
                        intPrvValue = intValue;
                        if(intPrvValue > intValue){
                            logger.info("intPrvValue > intValue.. Timer working..");
                        }
                        else{
                            logger.info("Something Wrong.. timer not working...");
                            throw new PageObjectsException("timer not working");
                        }
                    }

                }
            }
        }
    }
}
