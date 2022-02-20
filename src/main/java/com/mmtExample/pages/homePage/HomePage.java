package com.mmtExample.pages.homePage;

import com.mmtExample.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage implements IHomePage{

    @Override
    public void launch(String url) {
        getUrl(url);
    }

    @Override
    public void clickOnLogin() {
        click(LOGIN_BUTTON);
    }

    @Override
    public void selectMenu() {
        click(HOTEL_ICON);
    }
}
