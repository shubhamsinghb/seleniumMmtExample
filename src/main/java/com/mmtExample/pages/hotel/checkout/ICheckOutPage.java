package com.mmtExample.pages.hotel.checkout;

import org.openqa.selenium.By;

public interface ICheckOutPage {

    By HOTEL_NAME = By.xpath("(//div[contains(@class,\"hotel__info\")])[2]//p[1]");
    By CHECKIN_DATE = By.xpath("(((//div[contains(@class,\"booking__card\")])//div)[3]//p)[2]//span[1]");
    By CHECKOUT_DATE = By.xpath("(((//div[contains(@class,\"booking__card\")])//div)[3]//p)[4]//span[1]");
    By ROOM_TYPE = By.xpath("(((//div[contains(@class,\"booking__card\")])//div)[7]//p)[4]");
    By FIRST_NAME = By.xpath("((//div[contains(@class,\"booking__card\")])//div)[17]//span[1]");
    By LAST_NAME = By.xpath("((//div[contains(@class,\"booking__card\")])//div)[17]//span[2]");
    By EMAIL_PHONE = By.xpath("((//div[contains(@class,\"booking__card\")])//div)[17]//p[2]");
    By TOTAL_TARIFF = By.xpath("(//div[contains(@class, \"fare__summary__card\")]//span)[5]");
    By  GST = By.xpath("(//div[contains(@class, \"fare__summary__card\")]//span)[7]");
    By TOTAL_DUE = By.xpath("(//div[contains(@class, \"fare__summary__card\")]//span)[9]");

    String getHotelName();
    String getcheckInDate();
    String getCheckoutDate();
    String getRoomType();
    String getFirstName();
    String getLastName();
    String getEmailPhone();
    String getTariff();
    String getGst();
    String getTotalDue();

}
