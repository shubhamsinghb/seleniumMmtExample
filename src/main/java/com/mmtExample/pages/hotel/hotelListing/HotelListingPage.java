package com.mmtExample.pages.hotel.hotelListing;

import com.mmtExample.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HotelListingPage extends BasePage implements IHotelListingPage{


    @Override
    public void enterMinimumBudget(String minAmount) {
        inputText(INPUT_MIN_BUDGET, minAmount);
    }

    @Override
    public void enterMaximumBudget(String maxAmount) {
        inputText(INPUT_MAX_BUDGET,maxAmount);
    }

    @Override
    public void clickOnBudgetButton() {
        click(BUDGET_BUTTON);
    }

    @Override
    public void selectRatingCheckbox(String ratingToSelect) {
        By by = By.xpath("//span[text()='"+ratingToSelect+"']");
        clickOnElementUsingJavaScriptExecutor(by);
        waitForPageLoad();
    }

    @Override
    public void selectHotel(String hotel) {
//        List<WebElement> ele = getWebElements(GET_HOTEL_LIST);
//        System.out.println("it is" + ele.size());
//        System.out.println("it is" + ele.get(0));
//        System.out.println("it is" + ele.get(0).getText());
        By hoTelToBook = By.xpath("//span[text() = '"+hotel+"']");
        click(hoTelToBook);
    }

}
