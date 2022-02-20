package com.mmtExample.pages;

import com.mmtExample.driverManager.DriverFactory;
import com.mmtExample.pages.homePage.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void firstTest(){
        HomePage homePage = new HomePage();
        homePage.launch("https://www.makemytrip.com/");
        homePage.clickOnLogin();
        homePage.selectMenu();
    }
}
