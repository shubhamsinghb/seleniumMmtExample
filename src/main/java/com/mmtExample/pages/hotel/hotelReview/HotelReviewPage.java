package com.mmtExample.pages.hotel.hotelReview;


import com.mmtExample.bo.TravellerBO;
import com.mmtExample.pages.BasePage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class HotelReviewPage extends BasePage implements IHotelReviewPage {
    public static Logger logger = LogManager.getLogger(HotelReviewPage.class);


    @Override
    public String getHotelName() {
        return getText(HOTEL_NAME);
    }

    //Filling customer data. Will be filled using faker object
    @Override
    public void fillCustomerDetails(TravellerBO travellerBO){
        logger.info("Filling customer details");
        inputText(FIRST_NAME, travellerBO.getFirstName());
        inputText(LAST_NAME, travellerBO.getLastName());
        inputText(EMAIL, travellerBO.getEmail());
        inputText(MOBILE_NUMBER, travellerBO.getMobileNumber());
        if(travellerBO.isGst()){
            click(GST_CHECKBOX);
        }
        selectByVisibleText(TITLE, travellerBO.getTitle());
    }

    @Override
    public String getCheckinDate() {
        return getText(CHECKIN_DATE);
    }

    @Override
    public String getCheckOutDate() {
        return getText(CHECKOUT_DATE);
    }

    @Override
    public String getTravellerCount() {
        return getText(TRAVELLER_COUNT);
    }

    @Override
    public String getTariffAmount() {
        return getText(TARIFF_AMOUNT);
    }


    @Override
    public List<String> getRoomDetails() {
        List<WebElement> element = getWebElements(ROOM_DETAILS);
        List<String> hotelDetails = new ArrayList<>();
        for(WebElement ele : element){
            hotelDetails.add(ele.getText());
        }
        return hotelDetails;
    }

    public void clickOnPayNow(){
        scrollElementToView(PAY_NOW);
        clickOnElementUsingJavaScriptExecutor(PAY_NOW);

    }
}
