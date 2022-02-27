package com.mmtExample.driverManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxManager implements IBrowserManager{

    private static final Logger logger = LogManager.getLogger(FirefoxManager.class);
    @Override
    @Step
    public WebDriver getDriver() {
        logger.info("Starting firefox browser");
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }
}
