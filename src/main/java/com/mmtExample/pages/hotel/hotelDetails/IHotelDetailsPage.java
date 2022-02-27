package com.mmtExample.pages.hotel.hotelDetails;

import org.openqa.selenium.By;

import java.util.List;

public interface IHotelDetailsPage {

    By REVIEW_SELECTION_LINK = By.xpath("//p[text()='Review Selection']");
    By GET_ROOM_DETAILS_FROM_Selection_INFO = By.xpath("//div[@class='rmSelectionInfo ']//div[@class='rmSelectedCont ']//p");
    By SELECTION_INFO_CENTER_TEXT = By.xpath("//div[@class='rmSelectionInfo ']//div[contains(@class,'textCenter')]//p");

    String switchToHotelDetailTab();
    void goToReviewPage();
    List<String> selectFromDropDownRoom(String roomType, List<String> roomTexts) throws InterruptedException;
    List<String> getRoomDetailsFromInfoAfterSelection();
    Integer calculateTotalRoomPrice(List<String> roomCostList);
    List<String> getSelectInfoCenterText();
}
