package com.mmtExample.pages.hotel.hotelListing;

import org.openqa.selenium.By;

import java.util.List;

public interface IHotelListingPage {

    By BUDGET_BUTTON = By.xpath("//div[@class='range']//button");
    By INPUT_MIN_BUDGET = By.xpath("//input[@name='min']");
    By INPUT_MAX_BUDGET = By.xpath("//input[@name='max']");
    By GET_HOTEL_LIST = By.xpath("//div[@id=\"hotelListingContainer\"]");

    void enterMinimumBudget(String minAmount);
    void enterMaximumBudget(String maxAmount);
    void clickOnBudgetButton();
    void selectRatingCheckbox(String ratingToSelect);
    void selectHotel(String hotel, String checkinDate, String checkoutDate,int adults , List<Integer> ch);

}
