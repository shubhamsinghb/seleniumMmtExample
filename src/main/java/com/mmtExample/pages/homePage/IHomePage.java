package com.mmtExample.pages.homePage;

import com.mmtExample.enumConstants.MmtMainMenu;
import org.openqa.selenium.By;

public interface IHomePage {

    By LOGIN_BUTTON = By.xpath("//li[contains(@class,'userLoggedOut')]");
   // By  HOTEL_ICON = By.xpath("//li[@class='menu_Hotels']");

    void launch(String url);

    void clickOnLogin();

    // This method is top select the operation from menu. Book flight, hotel homestays etc
    void selectMenu(MmtMainMenu value);


}
