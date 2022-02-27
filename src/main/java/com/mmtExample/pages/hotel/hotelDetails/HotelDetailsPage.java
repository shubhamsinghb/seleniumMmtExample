package com.mmtExample.pages.hotel.hotelDetails;

import com.mmtExample.file.readers.PropertyReader;
import com.mmtExample.pages.BasePage;
import jdk.nashorn.internal.runtime.ListAdapter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HotelDetailsPage extends BasePage implements IHotelDetailsPage {


    @Override
    public String switchToHotelDetailTab() {
        return switchToNewOpenedTab();
    }

    @Override
    public List<String> selectFromDropDownRoom(String roomType, List<String> roomTexts) {
        List<String> roomCost =  new ArrayList<>();
        for(String roomText : roomTexts){
            By elementForRoomOccupantSelection = By.xpath("(//h2[@class='rmType__roomName'] " +
                    "[text()='"+roomType+"']//..//..//..//div[@class='slctRmDropdown__input'])[3]");
            scrollElementToView(elementForRoomOccupantSelection);
            clickOnElementUsingJavaScriptExecutor(elementForRoomOccupantSelection);
            By occupantDropdown = By.cssSelector(".slctRmDropdown__list");
            WebElement dropdowm = getElement(occupantDropdown);
            List<WebElement> elements = dropdowm.findElements(By.tagName("li"));
            for(WebElement element : elements){
                String[] roomOccupants = (element.getText().split("\\R"));
                if(roomOccupants[0].equalsIgnoreCase(roomText)){
                    roomCost.add(roomOccupants[1]);
                    clickOnElementUsingJavaScriptExecutor(element);
                    By clickOPnAddRoom = By.xpath("(//h2[@class='rmType__roomName'] [text()='"+roomType+"']//..//..//..//a[@class='rmPayable__dtl--addBtn'])[3]");
                    scrollElementToView(clickOPnAddRoom);
                    clickOnElementUsingJavaScriptExecutor(clickOPnAddRoom);
                    break;
                }
            }
        }

        return roomCost;
    }

    @Override
    public List<String> getRoomDetailsFromInfoAfterSelection() {
        List<String> dataList = new ArrayList<>();
        List<WebElement> ele = getWebElements(GET_ROOM_DETAILS_FROM_Selection_INFO);
        for(WebElement element : ele ){
            dataList.add(element.getText());
        }
        return dataList;
    }

    @Override
    public void goToReviewPage(){
        click(REVIEW_SELECTION_LINK);
    }

    public Integer calculateTotalRoomPrice(List<String> roomCostList){
        int cost =0;
        for(String costRoom : roomCostList){
            cost= (cost) + Integer.valueOf(costRoom.substring(1));
        }
        return cost;
    }

    public List<String> getSelectInfoCenterText(){
        List<String> dataList = new ArrayList<>();
        List<WebElement> ele = getWebElements(SELECTION_INFO_CENTER_TEXT);
        for(WebElement element : ele ){
            dataList.add(element.getText());
        }
        return dataList;
    }

}
