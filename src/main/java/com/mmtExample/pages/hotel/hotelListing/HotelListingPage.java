package com.mmtExample.pages.hotel.hotelListing;

import com.mmtExample.pages.BasePage;
import com.mmtExample.pages.hotel.hotelBooking.HotelBookingPage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import java.util.List;

public class HotelListingPage extends BasePage implements IHotelListingPage{
    public static Logger logger = LogManager.getLogger(HotelListingPage.class);

    //Enter the minimum amount in budget filer
    @Override
    public void enterMinimumBudget(String minAmount) {
        inputText(INPUT_MIN_BUDGET, minAmount);
    }

    //Enter the max amount in budget filer
    @Override
    public void enterMaximumBudget(String maxAmount) {
        inputText(INPUT_MAX_BUDGET,maxAmount);
    }

    @Override
    public void clickOnBudgetButton() {
        click(BUDGET_BUTTON);
    }

    //Select rating checkbox on the basis of user input
    @Override
    public void selectRatingCheckbox(String ratingToSelect) {
        By by = By.xpath("//span[text()='"+ratingToSelect+"']");
        clickOnElementUsingJavaScriptExecutor(by);
        waitForPageLoad();
    }

    @Override
    public void selectHotel(String hotel, String checkinDate, String checkoutDate,int adults , List<Integer> ch ) {
         By hoTelToBook = By.xpath("//span[text() = '"+hotel+"']");
            click(hoTelToBook);

    }

}
