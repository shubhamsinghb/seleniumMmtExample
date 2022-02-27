package com.mmtExample.pages.hotel.hotelBooking;

import com.mmtExample.enumConstants.TravellingFor;
import com.mmtExample.pages.BasePage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.time.Month;
import java.util.List;
import java.util.Locale;

public class HotelBookingPage extends BasePage implements IHotelBookingPage {

    public static Logger logger = LogManager.getLogger(HotelBookingPage.class);

    //Select the city are or hotel for the dropdown
    @Override
    public void selectCity(String city) {
        logger.info("Selecting city/area/hotel " +city);
        click(CITY_INPUT);
        inputText(CITY_TEXT_BOX, city);
        waitForMatchText(city, CITY_LIST);
        click(CITY_LIST);

    }

    //Select checkin date from checkin dropdoewn
    @Override
    public void selectCheckinDate(String checkInDate) {
        logger.info("Selecting checkin date " +checkInDate);
        String[] sf = checkInDate.split("-");
        String toCheck = Month.of(Integer.valueOf(sf[1])).name().toLowerCase(Locale.ENGLISH)+sf[2];
        while(!getText(CURRENT_MONTH_YEAR).toLowerCase().equalsIgnoreCase(toCheck)){
            click(NEXT_MONTH);
        }
        click(By.xpath("(//div[@class='DayPicker-Caption']//div)[1]//..//..//div[text()='"+sf[0]+"']"));
    }

    //Select checkout date from checkin dropdoewn
    @Override
    public void selectCheckOutDate(String checkoutDate) {
        logger.info("Selecting checkout date " +checkoutDate);
        String[] sf = checkoutDate.split("-");
        String toCheck = Month.of(Integer.valueOf(sf[1])).name().toLowerCase(Locale.ENGLISH)+sf[2];
        while(!getText(CURRENT_MONTH_YEAR).toLowerCase().equalsIgnoreCase(toCheck)){
            click(NEXT_MONTH);
        }
        click(By.xpath("(//div[@class='DayPicker-Caption']//div)[1]//..//..//div[text()='"+sf[0]+"']"));


    }

    // Select number og guests. This includes selecting
    // number of adults, number of children and age of each child
    @Override
    public void selectGuests(int adultCount, List<Integer> children) {
        logger.info("Selecting guest data " +adultCount + " adult"+ children.size() + " children");
        clicklWithoutWait(GUEST_DROPDOWN);
        click(By.cssSelector("ul[class^='guestCounter']:nth-of-type(1)>li:nth-of-type("+adultCount+")"));
        click(By.cssSelector("ul[class^='guestCounter']:nth-of-type(2)>li:nth-of-type("+(children.size()+1)+")"));
        for(int i=0; i<children.size(); i++){
            By selectElement = By.xpath("//select[@id='"+i+"'][@class=\"ageSelectBox\"]");
            selectByVisibleText(selectElement, String.valueOf(children.get(i)));
        }
        click(APPLY_GUEST_CHANGES);
    }

    //Select purpose of travelling
    @Override
    public void selectTravellingFor(String travellingFor) {
        click(TRAVELLING_FOR);
        if(travellingFor.equalsIgnoreCase(TravellingFor.WORK.getValue())){
            click(By.xpath("//li[text()='"+TravellingFor.WORK.getValue()+"']"));
        }else{
            click(By.xpath("//li[text()='"+TravellingFor.LEISURE.getValue()+"']"));
        }
    }

    @Override
    public void clickOnSearchHotel() {
        click(HOTEL_SEARCH);
        waitForPageLoad();
    }

    public  String getCurrentCitySelected(){
        return getAttributeValue(CITY_INPUT, "value");

    }

    public  String getCurrentCheckinDate(){
        return getAttributeValue(CHECKIN_INPUT, "value");

    }

    public  String getCurrentCheckoutDate(){
        return getAttributeValue(CHECKOUT_INPUT, "value");

    }

    public String getGuestDetails(){
        return getAttributeValue(GUEST_INPUT, "value");
    }

    public String expectedGuestDetails(int room, int adults, int children){
        return (room+" Room, "+adults+" Adults, "+children+" Child");
    }
}
