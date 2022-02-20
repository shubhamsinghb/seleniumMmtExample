package com.mmtExample.dataProviders;

import com.mmtExample.bo.HotelBookingBO;
import com.mmtExample.dataAggregators.HotelBookingDataAggregator;
import org.testng.annotations.DataProvider;

public class HotelBookingDataProvider {
    @DataProvider
    public Object[][] hotelBookingData(){
        HotelBookingDataAggregator hotelBookingDataAggregator = new HotelBookingDataAggregator();
        hotelBookingDataAggregator.read();
        Object[][] object= new Object[HotelBookingDataAggregator.getHotelBookingBoList().size()][1];
        int i=0;
        for (HotelBookingBO hotelBookingBO : HotelBookingDataAggregator.getHotelBookingBoList()){
            object[i][0]=hotelBookingBO;
            i++;
        }

        return object;
    }

}
