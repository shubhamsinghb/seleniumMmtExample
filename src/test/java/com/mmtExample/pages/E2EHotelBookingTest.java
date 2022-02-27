package com.mmtExample.pages;

import com.github.javafaker.Faker;
import com.mmtExample.bo.HotelBookingBO;
import com.mmtExample.bo.HotelBookingFilterBO;
import com.mmtExample.bo.TravellerBO;
import com.mmtExample.dataProviders.HotelBookingDataProvider;
import com.mmtExample.enumConstants.MmtMainMenu;
import com.mmtExample.file.readers.PropertyReader;
import com.mmtExample.pages.homePage.HomePage;
import com.mmtExample.pages.hotel.checkout.CheckoutPage;
import com.mmtExample.pages.hotel.hotelBooking.HotelBookingPage;
import com.mmtExample.pages.hotel.hotelDetails.HotelDetailsPage;
import com.mmtExample.pages.hotel.hotelListing.HotelListingPage;
import com.mmtExample.pages.hotel.hotelReview.HotelReviewPage;
import com.mmtExample.utils.DateUtils;
import com.mmtExample.utils.GSTHelper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.text.ParseException;
import java.util.List;

public class E2EHotelBookingTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(E2EHotelBookingTest.class);


    @Test(dataProvider = "hotelBookingData" , dataProviderClass = HotelBookingDataProvider.class , priority = 1)
    public void e2eHotelBookingTest(HotelBookingBO hotelBookingBO , HotelBookingFilterBO hotelBookingFilterBO, ITestContext iTestContext) throws InterruptedException, ParseException {
        logger.info("Starting test for booking hotel");
        logger.debug("");
        SoftAssert sft = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.setingItestContext(iTestContext);
        homePageOperations(homePage);
        HotelBookingPage hotelBookingPage = new HotelBookingPage();
        enterHotelBookingDetails(hotelBookingPage,hotelBookingBO);
        hotelBookingPage.clickOnSearchHotel();
        HotelListingPage hotelListingPage = new HotelListingPage();
        assertBookingDetails(hotelBookingPage,hotelBookingBO,sft);
        enterFilterDetails(hotelListingPage, hotelBookingFilterBO);
        hotelListingPage.selectHotel(hotelBookingFilterBO.getHotelToBook(),hotelBookingBO.getCheckIn(), hotelBookingBO.getCheckOut(),hotelBookingBO.getAdultCount() , hotelBookingBO.getChildren());
        HotelDetailsPage hotelDetailsPage = new HotelDetailsPage();
        hotelDetailsPage.switchToHotelDetailTab();
        List<String> roomCost = hotelDetailsPage.selectFromDropDownRoom(hotelBookingFilterBO.getRoomType(),hotelBookingFilterBO.getRoomOccupants());
        List<String> hotelDetailsFromSelectionInfo = hotelDetailsPage.getRoomDetailsFromInfoAfterSelection();
        hotelSelectionInfoAssert(hotelBookingFilterBO,hotelDetailsFromSelectionInfo,sft,roomCost);
        hotelDetailsPage.calculateTotalRoomPrice(roomCost);
        List<String> selectionInfoCenterData = hotelDetailsPage.getSelectInfoCenterText();
        String totalOccupants = hotelBookingBO.getAdultCount() + " Adults & " + hotelBookingBO.getChildren().size()+ " Child";
        sft.assertEquals(selectionInfoCenterData.get(0), totalOccupants);
        int totalRoomTariff = hotelDetailsPage.calculateTotalRoomPrice(roomCost);
        sft.assertEquals(selectionInfoCenterData.get(1).replaceAll(",","").substring(1), ""+totalRoomTariff,"Check for tariff failed");
        hotelDetailsPage.goToReviewPage();
        HotelReviewPage hotelReviewPage = new HotelReviewPage();
        TravellerBO travellerBO = createFakeTravellerData();
        hotelReviewPage.fillCustomerDetails(travellerBO);
        sft.assertEquals(hotelBookingFilterBO.getHotelToBook(), hotelReviewPage.getHotelName());
        hotelReviewPageAssert(hotelReviewPage, hotelBookingBO,hotelBookingFilterBO,sft,totalRoomTariff);
        hotelReviewPage.clickOnPayNow();
        CheckoutPage checkoutPage = new CheckoutPage();
        assertCheckout(checkoutPage, sft, hotelBookingFilterBO, hotelBookingBO, travellerBO,totalRoomTariff);
        sft.assertAll();
    }

    @Test(priority = 2)
    public void bookingTestFailedToCheckScreenShot(ITestContext iTestContext) {
        logger.info("Starting test for booking hotel");
        logger.debug("");
        SoftAssert sft = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.setingItestContext(iTestContext);
        homePageOperations(homePage);
        HotelBookingPage hotelBookingPage = new HotelBookingPage();
        sft.assertEquals(hotelBookingPage.getCurrentCitySelected(),"random");
        sft.assertAll();
    }

    public void homePageOperations(HomePage homePage){
        homePage.launch(PropertyReader.getProperty("baseUrl"));
        homePage.clickOnLogin();
        homePage.selectMenu(MmtMainMenu.HOTEL);
    }

    public void hotelReviewPageAssert(HotelReviewPage hotelReviewPage, HotelBookingBO hotelBookingBO
            , HotelBookingFilterBO hotelBookingFilterBO, SoftAssert sft, int totalRoomTariff) throws ParseException {
        logger.info("Asserting review page data");
        sft.assertEquals(hotelReviewPage.getCheckinDate(),DateUtils.changeDateFormat(hotelBookingBO.getCheckIn(),"dd-MM-yyyy" ,  "dd MMM yyyy"));
        sft.assertEquals(hotelReviewPage.getCheckOutDate(),DateUtils.changeDateFormat(hotelBookingBO.getCheckOut(),"dd-MM-yyyy" ,  "dd MMM yyyy"));
        sft.assertEquals(hotelReviewPage.getTariffAmount().replaceAll(",","").substring(2),""+totalRoomTariff, "Asserting tariff on review Page");
        sft.assertEquals(hotelReviewPage.getTravellerCount(), hotelBookingBO.getAdultCount()
                + " Adults, "+ hotelBookingBO.getChildren().size()+ " Child | " + hotelBookingFilterBO.getRoomOccupants().size() + " Rooms" );
        List<String> roomDetails = hotelReviewPage.getRoomDetails();
        sft.assertEquals(roomDetails.get(0), hotelBookingFilterBO.getRoomType().toUpperCase());
        sft.assertEquals(roomDetails.get(2), hotelBookingFilterBO.getRoomType().toUpperCase());
    }
    public void hotelSelectionInfoAssert(HotelBookingFilterBO hotelBookingFilterBO, List<String> hotelDetailsFromSelectionInfo, SoftAssert sft,List<String> roomCost ){
        logger.info("Asserting selection infor on review page");
        sft.assertEquals(hotelBookingFilterBO.getRoomType(), hotelDetailsFromSelectionInfo.get(0));
        sft.assertEquals(hotelBookingFilterBO.getRoomOccupants().get(0), hotelDetailsFromSelectionInfo.get(1));
        sft.assertEquals(roomCost.get(0)+"Per Night", hotelDetailsFromSelectionInfo.get(2).replaceAll(",",""),"Asserting per night cost room 1 in info");
        sft.assertEquals(hotelBookingFilterBO.getRoomType(), hotelDetailsFromSelectionInfo.get(3));
        sft.assertEquals(hotelBookingFilterBO.getRoomOccupants().get(1), hotelDetailsFromSelectionInfo.get(4));
        sft.assertEquals(roomCost.get(1)+"Per Night", hotelDetailsFromSelectionInfo.get(5).replaceAll(",",""),"Asserting per night cost room 2 in info");
    }

    public void assertBookingDetails(HotelBookingPage hotelBookingPage, HotelBookingBO hotelBookingBO , SoftAssert sft) throws ParseException {
        logger.info("Asserting booking dat entered");
        sft.assertTrue(hotelBookingPage.getCurrentCitySelected().contains(hotelBookingBO.getCity()));
        sft.assertTrue(hotelBookingPage.getCurrentCitySelected().contains(hotelBookingBO.getCity()));
        sft.assertEquals(hotelBookingPage.getCurrentCheckinDate(), DateUtils.changeDateFormat(hotelBookingBO.getCheckIn(),"dd-MM-yyyy" ,  "EEE, dd MMM yyyy"));
        sft.assertEquals(hotelBookingPage.getCurrentCheckoutDate(), DateUtils.changeDateFormat(hotelBookingBO.getCheckOut(),"dd-MM-yyyy" ,  "EEE, dd MMM yyyy"));
        sft.assertEquals(hotelBookingPage.getGuestDetails(), hotelBookingPage.expectedGuestDetails(hotelBookingBO.getRoom(),
                hotelBookingBO.getAdultCount(),hotelBookingBO.getChildren().size()));

    }

    public void enterFilterDetails(HotelListingPage hotelListingPage , HotelBookingFilterBO hotelBookingFilterBO){
        hotelListingPage.enterMinimumBudget(hotelBookingFilterBO.getMinAmount());
        hotelListingPage.enterMaximumBudget(hotelBookingFilterBO.getMaxAmount());
        hotelListingPage.clickOnBudgetButton();
        hotelListingPage.selectRatingCheckbox(hotelBookingFilterBO.getRating());
    }

    public void enterHotelBookingDetails(HotelBookingPage hotelBookingPage , HotelBookingBO hotelBookingBO){
        hotelBookingPage.selectCity(hotelBookingBO.getCity());
        hotelBookingPage.selectCheckinDate(hotelBookingBO.getCheckIn());
        hotelBookingPage.selectCheckOutDate(hotelBookingBO.getCheckOut());
        hotelBookingPage.selectGuests(hotelBookingBO.getAdultCount(),hotelBookingBO.getChildren());
        hotelBookingPage.selectTravellingFor(hotelBookingBO.getTravellingFor());
    }



    public void assertCheckout(CheckoutPage checkoutPage, SoftAssert sft, HotelBookingFilterBO hotelBookingFilterBO ,
                               HotelBookingBO hotelBookingBO, TravellerBO travellerBO, int totalRoomTariff) throws ParseException {

        sft.assertEquals(checkoutPage.getHotelName(),hotelBookingFilterBO.getHotelToBook() );
        sft.assertEquals(checkoutPage.getcheckInDate(),DateUtils.changeDateFormat(hotelBookingBO.getCheckIn(),"dd-MM-yyyy" ,  "EEE, dd MMM''yy") );
        sft.assertEquals(checkoutPage.getCheckoutDate(),DateUtils.changeDateFormat(hotelBookingBO.getCheckOut(),"dd-MM-yyyy" ,  "EEE, dd MMM''yy"));
        sft.assertEquals(checkoutPage.getRoomType().substring(0,checkoutPage.getRoomType().length()-1), hotelBookingFilterBO.getRoomType());
        sft.assertEquals(checkoutPage.getFirstName(),travellerBO.getFirstName());
        sft.assertEquals(checkoutPage.getLastName(),travellerBO.getLastName());
        String[] email_phone = checkoutPage.getEmailPhone().split("\\|");
        sft.assertEquals(email_phone[0].trim(), travellerBO.getEmail());
        int calculateGst = GSTHelper.calculateGst(totalRoomTariff);
        sft.assertEquals(checkoutPage.getTariff().replaceAll(",",""), String.valueOf(totalRoomTariff+10), "Asserting tariff on checkout page");
        sft.assertEquals(checkoutPage.getGst().replaceAll(",",""), String.valueOf(calculateGst), "Asserting gst ckeckout page");
        sft.assertEquals(checkoutPage.getTotalDue().replaceAll(",",""), String.valueOf(calculateGst+totalRoomTariff+10),"Asserting total due ckeckout page");
    }

    public TravellerBO createFakeTravellerData(){
        TravellerBO travellerBO = new TravellerBO();
        Faker faker = new Faker();
        travellerBO.setTitle("Mr");
        travellerBO.setFirstName(faker.name().firstName());
        travellerBO.setEmail(faker.internet().emailAddress());
        travellerBO.setLastName(faker.name().lastName());
        travellerBO.setLastName(faker.name().lastName());
        travellerBO.setMobileNumber(faker.phoneNumber().subscriberNumber(10));
        travellerBO.setGst(false);

        return travellerBO;

    }
}
