package com.mmtExample.pages;

import com.mmtExample.driverManager.DriverFactory;
import com.mmtExample.file.readers.PropertyReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class BasePage {

    WebDriver driver;

    protected BasePage(){
        this.driver = DriverFactory.getBrowserInstance();
    }

    private WebElement findElement(By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50), Duration.ofMillis(250));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void setiTestContext(ITestContext iTestContext){
        iTestContext.setAttribute("driver",driver);
    }

    public WebElement getElement(By by){
        return findElement(by);
    }

    public void scrollElementToView(By by){
        WebElement element = findElement(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void clicklWithoutWait(By by){
        driver.findElement(by).click();

    }

    public Select getSelectDropdown(By by){
        Select select = new Select(driver.findElement(by));
        return select;
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
    protected String getAttributeValue(By by, String attribute){
        return findElement(by).getAttribute("value");
    }

    protected void waitForPageLoad(){
        JavascriptExecutor javascriptExecutor= (JavascriptExecutor) driver;
        boolean b = false;
        while(!b && PropertyReader.getProperty("browser").equals("chrome")){
            b = javascriptExecutor.executeScript("return document.readyState").equals("complete");
        }

    }

    protected void waitForMatchText(String value, By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20), Duration.ofMillis(250));
        wait.until(ExpectedConditions.textMatches(by, Pattern.compile("^"+value)));


    }

    protected void selectByVisibleText(By by, String text){
        Select select = new Select(findElement(by));
        select.selectByVisibleText(text);
    }

    protected void clickOnElementUsingJavaScriptExecutor(By by){
        WebElement element = findElement(by);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    protected void clickOnElementUsingJavaScriptExecutor(WebElement webElement){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", webElement);
    }

    protected List<WebElement> getWebElements(By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20), Duration.ofMillis(250));
        wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(by)));
        return driver.findElements(by);
    }

    protected String switchToNewOpenedTab(){
        String currentWindow = driver.getWindowHandle();
        for(String window : driver.getWindowHandles()){
            if(!window.equals(currentWindow)){
                driver.switchTo().window(window);
            }
        }
        waitForPageLoad();
        return driver.getCurrentUrl();
    }

    protected void implicitlyWait(int time){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
    }



}
