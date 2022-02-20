package com.mmtExample.pages;

import com.mmtExample.driverManager.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Pattern;

public class BasePage {

    WebDriver driver;

    protected BasePage(){
        this.driver = DriverFactory.getBrowserInstance();
    }

    private WebElement findElement(By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20), Duration.ofMillis(250));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected void getUrl(String url){
        driver.get(url);
    }

    protected void click(By by){
        findElement(by).click();
    }

    protected void inputText(By by, String textToInput){
        findElement(by).sendKeys(textToInput);
    }

    protected String getText(By by){
        return findElement(by).getText();
    }

    protected void waitForPageLoad(){
        JavascriptExecutor javascriptExecutor= (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("return document.readyState").equals("completed");
    }

    protected void waitForMatchText(String city, By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20), Duration.ofMillis(250));
        wait.until(ExpectedConditions.textMatches(by, Pattern.compile("^"+city)));
    }

}
