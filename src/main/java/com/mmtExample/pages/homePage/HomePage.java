package com.mmtExample.pages.homePage;

import com.mmtExample.enumConstants.MmtMainMenu;
import com.mmtExample.pages.BasePage;
import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.ITestContext;


public class HomePage extends BasePage implements IHomePage{


    public static Logger logger = LogManager.getLogger(HomePage.class);
    @Override
    @Step
    public void launch(String url) {
        logger.info("Navigating to URL :: " + url);
        getUrl(url);
    }

    @Override
    @Step
    public void clickOnLogin() {
        click(LOGIN_BUTTON);
    }

    @Override
    @Step
    public void selectMenu(MmtMainMenu value) {
        logger.info("Selecting " + value);
        click(By.xpath("//li[@class='menu_"+ value.getValue() +"']"));
        waitForPageLoad();

    }

    public void setingItestContext(ITestContext itestContext){
       setiTestContext(itestContext);
    }
}
