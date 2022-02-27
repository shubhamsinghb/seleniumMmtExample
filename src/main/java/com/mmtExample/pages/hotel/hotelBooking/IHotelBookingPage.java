package com.mmtExample.pages.hotel.hotelBooking;

import org.openqa.selenium.By;

import java.util.List;
import java.util.ListResourceBundle;

public interface IHotelBookingPage {

    By CITY_INPUT = By.id("city");
    By CHECKIN_INPUT = By.id("checkin");
    By CHECKOUT_INPUT = By.id("checkout");
    By GUEST_INPUT = By.id("guest");
    By CITY_TEXT_BOX = By.xpath("//input[contains(@placeholder,'city')]");
    By CITY_LIST = By.xpath("//p[text()='SUGGESTIONS ']//..//..//li[1]");
    By CURRENT_MONTH_YEAR = By.xpath("(//div[@class='DayPicker-Caption']//div)[1]");
    By NEXT_MONTH = By.xpath("//span[@aria-label='Next Month']");
    By GUEST_DROPDOWN = By.id("guest");
    By APPLY_GUEST_CHANGES = By.xpath("//button[text()='APPLY']");
    By TRAVELLING_FOR = By.xpath("//span[text()='Travelling For']");
    By HOTEL_SEARCH = By.xpath("//button[text()='Search']");


    void selectCity(String city);
    void selectCheckinDate(String checkinDate);
    void selectCheckOutDate(String checkoutDate);
    void selectGuests(int adultCount, List<Integer> children);
    void selectTravellingFor(String travellingFor);
    void clickOnSearchHotel();

}
