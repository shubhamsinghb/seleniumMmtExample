package com.mmtExample.dataAggregators;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mmtExample.bo.HotelBookingBO;
import com.mmtExample.bo.HotelBookingFilterBO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class HotelBookingFilterAggregator extends HotelBookingAbstractDataAggregator{

    String filePath="src/test/resources/HotelBookingFilter.json";
    private static List<HotelBookingFilterBO> hotelBookingFilterBOList = new ArrayList<>();

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

            HotelBookingFilterBO hotelBookingFilterBO= new HotelBookingFilterBO();
            JsonObject jsonObject= (JsonObject) element ;
            hotelBookingFilterBO.setMinAmount(replaceInDataFromAggregator(jsonObject.get("minAmount")));
            hotelBookingFilterBO.setMaxAmount(replaceInDataFromAggregator(jsonObject.get("maxAmount")));
            hotelBookingFilterBO.setRating(replaceInDataFromAggregator(jsonObject.get("rating")));
            hotelBookingFilterBO.setHotelToBook(replaceInDataFromAggregator(jsonObject.get("hotelToBook")));
            hotelBookingFilterBO.setRoomType(replaceInDataFromAggregator(jsonObject.get("roomType")));
            JsonArray rm = jsonObject.get("roomOccupants").getAsJsonArray();
            List<String> roomOccupants= new ArrayList<>();
            for(JsonElement roomOccupant : rm){
                roomOccupants.add(replaceInDataFromAggregator((roomOccupant)));
            }
            hotelBookingFilterBO.setRoomOccupants(roomOccupants);
            hotelBookingFilterBOList.add(hotelBookingFilterBO);
        }


    }

    public static List<HotelBookingFilterBO> getHotelBookingFilterBOList() {
        return hotelBookingFilterBOList;
    }

    public static void main(String []args){
        HotelBookingFilterAggregator r= new HotelBookingFilterAggregator();
        r.read();
    }
}
