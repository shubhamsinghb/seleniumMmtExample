package com.mmtExample.pages.hotel.hotelReview;

import com.mmtExample.bo.TravellerBO;
import org.openqa.selenium.By;

import java.util.List;

public interface IHotelReviewPage {

    By HOTEL_NAME = By.xpath("//div[@class='htlInfo__dtlLeft']//h3");
    By FIRST_NAME = By.id("fName");
    By TITLE = By.id("title");
    By LAST_NAME = By.id("lName");
    By EMAIL = By.id("email");
    By MOBILE_NUMBER = By.id("mNo");
    By GST_CHECKBOX =By.xpath("//label[@for=\"gstVisible\"]");
    By PAY_NOW = By.xpath("//a[contains(@class, 'btnContinuePayment')]");
    By CHECKIN_DATE = By.xpath("//div[@class= 'chkCont__col'][1]//div[1]//span[2]");
    By CHECKOUT_DATE = By.xpath("//div[@class= 'chkCont__col'][1]//div[3]//span[2]");
    By TRAVELLER_COUNT = By.xpath("//div[@class= 'chkCont__col'][2]//p[1]");
    By TARIFF_AMOUNT = By.xpath("(//div[@class=\"reviewContainer__right\"]//div[@class=\"prcBreakup__row\"]//div//div[@class= 'prcBreakup__rht'])[1]");
    By ROOM_DETAILS = By.xpath("//div[@class=\"roomDtlCard__head\"]//div//p");

    String getHotelName();
    void fillCustomerDetails(TravellerBO travellerBO);
    String getCheckinDate();
    String getCheckOutDate();
    String getTravellerCount();
    String getTariffAmount();
    List<String> getRoomDetails();
}
