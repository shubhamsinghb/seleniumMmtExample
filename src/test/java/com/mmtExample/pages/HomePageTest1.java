package com.mmtExample.pages;

import com.mmtExample.enumConstants.MmtMainMenu;
import com.mmtExample.pages.homePage.HomePage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class HomePageTest1 extends BaseTest {
        private static final Logger logger = LogManager.getLogger(HomePageTest1.class);
    @Test
    @Epic("Test for booking creation in hotel")
    @Feature("Able to go to hotel")
    public void firstTest1(){
        logger.info("start trest for mmt");
        HomePage homePage = new HomePage();
        homePage.launch("https://www.makemytrip.com/");
        homePage.clickOnLogin();
        homePage.selectMenu(MmtMainMenu.HOTEL);
    }
}
