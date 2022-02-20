package com.mmtExample.pages.hotel.hotelBooking;

import com.mmtExample.pages.BasePage;

public class HotelBookingPage extends BasePage implements IHotelBookingPage {


    @Override
    public void selectCity(String city) {
        click(CITY_INPUT);
        inputText(CITY_TEXT_BOX, city);
        waitForMatchText(city, CITY_LIST);
        click(CITY_LIST);

    }
}
