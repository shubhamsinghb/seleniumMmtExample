package com.mmtExample.dataProviders;

import com.mmtExample.bo.HotelBookingBO;
import com.mmtExample.bo.HotelBookingFilterBO;
import com.mmtExample.dataAggregators.HotelBookingDataAggregator;
import com.mmtExample.dataAggregators.HotelBookingFilterAggregator;
import org.testng.annotations.DataProvider;

public class HotelBookingDataProvider {
    @DataProvider
    public Object[][] hotelBookingData(){
        HotelBookingDataAggregator hotelBookingDataAggregator = new HotelBookingDataAggregator();
        hotelBookingDataAggregator.read();
        HotelBookingFilterAggregator hotelBookingFilterAggregator = new HotelBookingFilterAggregator();
        hotelBookingFilterAggregator.read();
        Object[][] object= new Object[HotelBookingDataAggregator.getHotelBookingBoList().size()][2];
        int i=0;
        for (HotelBookingBO hotelBookingBO : HotelBookingDataAggregator.getHotelBookingBoList()){
            object[i][0]=hotelBookingBO;
            i++;
        }
        i=0;
        for (HotelBookingFilterBO hotelBookingFilterBO : HotelBookingFilterAggregator.getHotelBookingFilterBOList()){
            object[i][1]=hotelBookingFilterBO;
            i++;
        }

        return object;
    }

    @DataProvider
    public Object[][] hotelBookingDataFailed(){
        HotelBookingDataAggregator hotelBookingDataAggregator = new HotelBookingDataAggregator();
        hotelBookingDataAggregator.read();
        HotelBookingFilterAggregator hotelBookingFilterAggregator = new HotelBookingFilterAggregator();
        hotelBookingFilterAggregator.read();
        Object[][] object= new Object[HotelBookingDataAggregator.getHotelBookingBoList().size()][2];
        int i=0;
        for (HotelBookingBO hotelBookingBO : HotelBookingDataAggregator.getHotelBookingBoList()){
            object[i][0]=hotelBookingBO;
            i++;
        }
        i=0;
        for (HotelBookingFilterBO hotelBookingFilterBO : HotelBookingFilterAggregator.getHotelBookingFilterBOList()){
            object[i][1]=hotelBookingFilterBO;
            i++;
        }

        return object;
    }

}
