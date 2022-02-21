package com.mmtExample.pages.hotel.hotelBooking;

import com.mmtExample.enumConstants.TravellingFor;
import com.mmtExample.pages.BasePage;
import org.openqa.selenium.By;

import java.time.Month;
import java.util.List;
import java.util.Locale;

public class HotelBookingPage extends BasePage implements IHotelBookingPage {


    @Override
    public void selectCity(String city) {
        click(CITY_INPUT);
        inputText(CITY_TEXT_BOX, city);
        waitForMatchText(city, CITY_LIST);
        click(CITY_LIST);

    }

    @Override
    public void selectCheckinDate(String checkInDate) {
        String[] sf = checkInDate.split("-");
        String toCheck = Month.of(Integer.valueOf(sf[1])).name().toLowerCase(Locale.ENGLISH)+sf[2];
        while(!getText(CURRENT_MONTH_YEAR).toLowerCase().equalsIgnoreCase(toCheck)){
            click(NEXT_MONTH);
        }
        click(By.xpath("(//div[@class='DayPicker-Caption']//div)[1]//..//..//div[text()='"+sf[0]+"']"));
        waitForPageLoad();
    }

    @Override
    public void selectCheckOutDate(String checkoutDate) {
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

        clicklWithoutWait(GUEST_DROPDOWN);
        click(By.cssSelector("ul[class^='guestCounter']:nth-of-type(1)>li:nth-of-type("+adultCount+")"));
        click(By.cssSelector("ul[class^='guestCounter']:nth-of-type(2)>li:nth-of-type("+(children.size()+1)+")"));
        for(int i=0; i<children.size(); i++){
            By selectElement = By.xpath("//select[@id='"+i+"'][@class=\"ageSelectBox\"]");
            selectByVisibleText(selectElement, String.valueOf(children.get(i)));
        }
        click(APPLY_GUEST_CHANGES);
    }

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


}
