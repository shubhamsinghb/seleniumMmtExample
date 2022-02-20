package com.mmtExample.pages.hotel.hotelBooking;

import org.openqa.selenium.By;

public interface IHotelBookingPage {

    By CITY_INPUT = By.id("city");
    By CITY_TEXT_BOX = By.xpath("//input[contains(@placeholder,'city')]");
    By CITY_LIST = By.xpath("//p[text()='SUGGESTIONS ']//..//..//li[1]");

    void selectCity(String city);


}
