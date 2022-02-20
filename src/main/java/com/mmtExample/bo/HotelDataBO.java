package com.mmtExample.bo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HotelDataBO {
    private String city;
    private String checkInDate;
    private String checkOutDate;
    private int adultCount;
    private List<Integer> children;
    private String travellingFor;



}
