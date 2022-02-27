package com.mmtExample.dataAggregators;

import com.google.gson.JsonElement;

public abstract class HotelBookingAbstractDataAggregator {

    public String replaceInDataFromAggregator(JsonElement element){
        return element.toString().replace("\"","");
    }

    public abstract void read();
}
