package com.mmtExample.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class HotelBookingBO {
    private String city;
    private String checkIn;
    private String checkOut;
    private int adultCount;
    private List<Integer> children;
    private String travellingFor;
    private int room;



}
