package com.mmtExample.pages;

import com.mmtExample.bo.HotelBookingBO;
import com.mmtExample.bo.HotelBookingFilterBO;
import com.mmtExample.dataProviders.HotelBookingDataProvider;
import com.mmtExample.enumConstants.MmtMainMenu;
import com.mmtExample.file.readers.PropertyReader;
import com.mmtExample.pages.homePage.HomePage;
import com.mmtExample.pages.hotel.hotelBooking.HotelBookingPage;
import com.mmtExample.pages.hotel.hotelDetails.HotelDetailsPage;
import com.mmtExample.pages.hotel.hotelListing.HotelListingPage;
import com.mmtExample.utils.DateUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class HomePageTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(HomePageTest.class);


    @Test(dataProvider = "hotelBookingData" , dataProviderClass = HotelBookingDataProvider.class)
    public void firstTest(HotelBookingBO hotelBookingBO , HotelBookingFilterBO hotelBookingFilterBO) throws InterruptedException, ParseException {
        logger.info("start trest for mmt");
        logger.debug("");
        SoftAssert sft = new SoftAssert();
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
        HotelListingPage hotelListingPage = new HotelListingPage();
        hotelListingPage.enterMinimumBudget(hotelBookingFilterBO.getMinAmount());
        hotelListingPage.enterMaximumBudget(hotelBookingFilterBO.getMaxAmount());
        hotelListingPage.clickOnBudgetButton();
        hotelListingPage.selectRatingCheckbox(hotelBookingFilterBO.getRating());

        sft.assertTrue(hotelBookingPage.getCurrentCitySelected().contains(hotelBookingBO.getCity()));
        sft.assertTrue(hotelBookingPage.getCurrentCitySelected().contains(hotelBookingBO.getCity()));
        sft.assertEquals(hotelBookingPage.getCurrentCheckinDate(), DateUtils.changeDateFormat(hotelBookingBO.getCheckIn()));
        sft.assertEquals(hotelBookingPage.getCurrentCheckoutDate(), DateUtils.changeDateFormat(hotelBookingBO.getCheckOut()));
        sft.assertEquals(hotelBookingPage.getGuestDetails(), hotelBookingPage.expectedGuestDetails(hotelBookingBO.getRoom(),
                hotelBookingBO.getAdultCount(),hotelBookingBO.getChildren().size()));
        hotelListingPage.selectHotel(hotelBookingFilterBO.getHotelToBook());
        HotelDetailsPage hotelDetailsPage = new HotelDetailsPage();
        System.out.println(hotelDetailsPage.switchToHotelDetailTab());
        List<String> roomCost = hotelDetailsPage.selectFromDropDownRoom(hotelBookingFilterBO.getRoomType(),hotelBookingFilterBO.getRoomOccupants());
        for(String r : roomCost){
            System.out.println("rrom cost ::" + r);
        }
        hotelDetailsPage.goToReviewPage();

       // drive(//h2[@class='rmType__roomName'] [text()='Premier City View with Bathtub']//..//..//..//div[@class="slctRmDropdown__input"])[1]
        //(//h2[@class='rmType__roomName'] [text()='Premier City View with Bathtub']//..//..//..//a[@class="rmPayable__dtl--addBtn"])[2]

        sft.assertAll();
    }
}
