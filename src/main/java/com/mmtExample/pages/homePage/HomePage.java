package com.mmtExample.pages.homePage;

import com.mmtExample.enumConstants.MmtMainMenu;
import com.mmtExample.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage implements IHomePage{

    @Override
    @Step
    public void launch(String url) {
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
        click(By.xpath("//li[@class='menu_"+ value.getValue() +"']"));
        waitForPageLoad();

    }
}
