package com.mmtExample.driverManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeManager implements IBrowserManager{

    private static final Logger logger = LogManager.getLogger(ChromeManager.class);

    @Override
    @Step
    public WebDriver getDriver() {
        logger.info("Starting chrome Browser");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        return driver;
    }
}
