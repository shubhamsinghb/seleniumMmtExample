package com.mmtExample.pages;

import com.mmtExample.driverManager.DriverFactory;
import com.mmtExample.file.readers.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    public void setup(){
        PropertyReader reader = new PropertyReader();
        reader.read();
    }

    @BeforeMethod
    public void launchBrowser(){
        WebDriver driver = DriverFactory.createDriver();
    }

    @AfterMethod
    public void closeBrowser(){
        DriverFactory.closeBrowserInstance();
    }
}
