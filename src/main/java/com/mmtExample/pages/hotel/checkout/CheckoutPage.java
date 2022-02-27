package com.mmtExample.pages.hotel.checkout;

import com.mmtExample.pages.BasePage;

public class CheckoutPage extends BasePage implements ICheckOutPage {
    @Override
    public String getHotelName() {
        return getText(HOTEL_NAME);
    }

    @Override
    public String getcheckInDate() {
        return getText(CHECKIN_DATE);
    }

    @Override
    public String getCheckoutDate() {
        return getText(CHECKOUT_DATE);
    }

    @Override
    public String getRoomType() {
        return getText(ROOM_TYPE);
    }

    @Override
    public String getFirstName() {
        return getText(FIRST_NAME);
    }

    @Override
    public String getLastName() {
        return getText(LAST_NAME);
    }

    @Override
    public String getEmailPhone() {
        return getText(EMAIL_PHONE);
    }

    @Override
    public String getTariff() {
        return getText(TOTAL_TARIFF);
    }

    @Override
    public String getGst() {
        return getText(GST);
    }

    @Override
    public String getTotalDue() {
        return getText(TOTAL_DUE);
    }
}
