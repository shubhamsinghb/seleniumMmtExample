package com.mmtExample.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static String changeDateFormat(String travelDate) throws ParseException {
        SimpleDateFormat formatDate=new SimpleDateFormat("dd-MM-yyyy");
        Date date=formatDate.parse(travelDate);
        String pattern = "EEE, dd MMM yyyy";
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(pattern, new Locale("En", "En"));
        return simpleDateFormat.format(date);

        //Sat, 2 Apr 2022
    }
    public static void main(String [] args) throws ParseException {
        System.out.println(changeDateFormat("12-04-2022"));
    }
}
