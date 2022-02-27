package com.mmtExample.utils;

public class GSTHelper {


    //This is ahelper method to calculate gst on the basis of govt rules
    public static int calculateGst(int totalRoomTeriff){
        int gstValue= 0;
        if(totalRoomTeriff<1000 && totalRoomTeriff<=7500 ){
            gstValue= (int) (0.12*totalRoomTeriff);
            return gstValue;
        }else if (totalRoomTeriff>7500){
            gstValue = (int)(0.18*totalRoomTeriff);
            return gstValue;
        }
        return gstValue;
    }
}
