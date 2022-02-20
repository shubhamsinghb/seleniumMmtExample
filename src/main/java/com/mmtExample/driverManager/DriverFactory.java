package com.mmtExample.driverManager;

import com.mmtExample.file.readers.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
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
        driverThreadLocal.get().close();
    }
}
