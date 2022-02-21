package com.mmtExample.pages;

import com.mmtExample.bo.HotelBookingBO;
import com.mmtExample.dataProviders.HotelBookingDataProvider;
import com.mmtExample.enumConstants.MmtMainMenu;
import com.mmtExample.file.readers.PropertyReader;
import com.mmtExample.pages.homePage.HomePage;
import com.mmtExample.pages.hotel.hotelBooking.HotelBookingPage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(HomePageTest.class);


    @Test(dataProvider = "hotelBookingData" , dataProviderClass = HotelBookingDataProvider.class)
    public void firstTest(HotelBookingBO hotelBookingBO) throws InterruptedException {
        logger.info("start trest for mmt");
        System.out.println(hotelBookingBO.toString());
        HomePage homePage = new HomePage();
        homePage.launch(PropertyReader.getProperty("baseUrl"));
        homePage.clickOnLogin();
        homePage.selectMenu(MmtMainMenu.HOTEL);
        HotelBookingPage hotelBookingPage = new HotelBookingPage();
        hotelBookingPage.selectCity(hotelBookingBO.getCity());
        hotelBookingPage.selectCheckinDate(hotelBookingBO.getCheckIn());
        hotelBookingPage.selectCheckOutDate(hotelBookingBO.getCheckOut());
        hotelBookingPage.selectGuests(hotelBookingBO.getAdultCount(),hotelBookingBO.getChildren());
        hotelBookingPage.selectTravellingFor(hotelBookingBO.getTravellingFor());
        hotelBookingPage.clickOnSearchHotel();



    }
}
