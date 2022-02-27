package com.mmtExample.dataAggregators;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mmtExample.bo.HotelBookingBO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class HotelBookingDataAggregator extends HotelBookingAbstractDataAggregator {
    String filePath="src/test/resources/hotelData.json";
    private static List<HotelBookingBO> hotelBookingBoList = new ArrayList<>();

    @Override
    public void read() {
        FileReader reader=null;
        try{
            reader  = new FileReader(new File(filePath));
        }catch (FileNotFoundException e){
            e.printStackTrace();

        }

        JsonElement jsonElement= JsonParser.parseReader(reader);
        JsonArray jsonArray= jsonElement.getAsJsonArray();

        for(Object element : jsonArray){

            HotelBookingBO hotelBookingBO= new HotelBookingBO();
            JsonObject jsonObject= (JsonObject) element ;
            hotelBookingBO.setCity(jsonObject.get("city").toString().replace("\"",""));
            hotelBookingBO.setCheckIn(jsonObject.get("checkIn").toString().replace("\"",""));
            hotelBookingBO.setCheckOut(jsonObject.get("checkOut").toString().replace("\"",""));
            hotelBookingBO.setAdultCount(jsonObject.get("adultCount").getAsInt());
            hotelBookingBO.setTravellingFor(jsonObject.get("travellingFor").toString().replace("\"",""));
            hotelBookingBO.setRoom(jsonObject.get("room").getAsInt());
            JsonArray ch = jsonObject.get("children").getAsJsonArray();
            List<Integer> childrenAge= new ArrayList<>();
            for(JsonElement children : ch){
                childrenAge.add(children.getAsInt());
            }
            hotelBookingBO.setChildren(childrenAge);
            hotelBookingBoList.add(hotelBookingBO);
        }


    }

    public static List<HotelBookingBO> getHotelBookingBoList() {
        return hotelBookingBoList;
    }

    public static void main(String []args){
        HotelBookingDataAggregator r= new HotelBookingDataAggregator();
        r.read();
        System.out.println(getHotelBookingBoList());
    }
}
