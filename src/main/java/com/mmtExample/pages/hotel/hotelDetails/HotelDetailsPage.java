package com.mmtExample.pages.hotel.hotelDetails;

import com.mmtExample.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HotelDetailsPage extends BasePage implements IHotelDetailsPage {


    @Override
    public String switchToHotelDetailTab() {
        return switchToNewOpenedTab();
    }

//    @Override
//    public List<String> selectRoom(String roomType, List<String> roomText) {
//        List<String> roomCost = new ArrayList<>();
//
//        scrollElementToView(by);
//        clickOnElementUsingJavaScriptExecutor(by);
//        By by2 = By.cssSelector(".slctRmDropdown__list");
//        By by2 = By.cssSelector(".slctRmDropdown__list");
//        List<String>  forRoomCost= selectFromDropDownRoom(by2 ,roomText);
//        By by1 = By.xpath("(//h2[@class='rmType__roomName'] [text()='"+roomType+"']//..//..//..//a[@class='rmPayable__dtl--addBtn'])[3]");
//        scrollElementToView(by1);
//        clickOnElementUsingJavaScriptExecutor(by1);
//        return forRoomCost;
//
//    }

//    protected String selectFromDropDownRoom(By by, String text){
//        WebElement dropdowm = getElement(by);
//        List<WebElement> elements = dropdowm.findElements(By.tagName("li"));
//        for(WebElement element : elements){
//            System.out.println(element.getText());
//            if(element.getText().contains(text)){
//                String s = element.getText();
//                clickOnElementUsingJavaScriptExecutor(element);
//                return s;
//            }
//        }
//        return null;
//    }

    @Override
    public List<String> selectFromDropDownRoom(String roomType, List<String> roomTexts){
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
                    roomCost.add(roomOccupants[1].substring(1));
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

    public void goToReviewPage(){
        click(REVIEW_SELECTION_LINK);
    }
}
