package com.mmtExample.driverManager;

import com.mmtExample.file.readers.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;



public class DriverFactory {
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    @Step
    public static WebDriver createDriver(){

        WebDriver driver;
        String browser = PropertyReader.getProperty("browser");
        switch (browser){

            case("chrome"):
                driver = new ChromeManager().getDriver();
                break;
            case("firefox"):
                driver = new FirefoxManager().getDriver();
                break;
            default:
                throw new IllegalArgumentException(browser + " is not supported");
        }
        driverThreadLocal.set(driver);
        return driver;
    }

    public static WebDriver getBrowserInstance(){
        return driverThreadLocal.get();
    }

    public static void closeBrowserInstance(){
        driverThreadLocal.get().quit();
    }
}
