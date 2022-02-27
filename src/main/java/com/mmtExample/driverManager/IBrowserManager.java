package com.mmtExample.driverManager;

import org.openqa.selenium.WebDriver;

public interface IBrowserManager {

    //This method will be used to get driver. Each driver will need to implement this interface
    WebDriver getDriver();
}
