package com.mmtExample.pages.hotel.hotelDetails;

import org.openqa.selenium.By;

import java.util.List;

public interface IHotelDetailsPage {

    By REVIEW_SELECTION_LINK = By.xpath("//p[text()='Review Selection']");

    String switchToHotelDetailTab();
    //List<String> selectRoom(String roomType, List<String> roomText);
     List<String> selectFromDropDownRoom(String roomType, List<String> roomTexts);
}
