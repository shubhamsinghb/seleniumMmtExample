package com.mmtExample.pages;

import com.mmtExample.driverManager.DriverFactory;
import com.mmtExample.file.manager.DirectoryManager;
import com.mmtExample.file.readers.PropertyReader;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    public void setup(){
        DirectoryManager.clearDirectory("allure-results","allure-reports","output");
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        PropertyReader reader = new PropertyReader();
        reader.read();

        BasicConfigurator.configure();

    }

    @BeforeMethod
    public void launchBrowser(){
        WebDriver driver = DriverFactory.createDriver();
    }

//    @AfterMethod
//    public void closeBrowser(){
//        DriverFactory.closeBrowserInstance();
//    }
}
