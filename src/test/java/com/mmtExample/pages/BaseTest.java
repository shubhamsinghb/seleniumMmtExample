package com.mmtExample.pages;

import com.mmtExample.driverManager.DriverFactory;
import com.mmtExample.file.manager.DirectoryManager;
import com.mmtExample.file.readers.PropertyReader;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    public static Logger logger = LogManager.getLogger(BaseTest.class);
    @BeforeSuite
    public void setup(){
        logger.info("Starting brfoure suite. Cleaning up directories");
        DirectoryManager.clearDirectory("allure-results","allure-reports","output");
        logger.info("configuring loger configurations");
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        logger.info("Reading pconfig properties");
        PropertyReader reader = new PropertyReader();
        reader.read();

    }

    @BeforeMethod
    public void launchBrowser(){
        logger.info("Starting browser Instance");
        WebDriver driver = DriverFactory.createDriver();
    }

    @AfterMethod
    public void closeBrowser(){
        logger.info("Closing browser Instance");
        DriverFactory.closeBrowserInstance();
    }
}
