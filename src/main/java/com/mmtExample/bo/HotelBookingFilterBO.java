package com.mmtExample.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class HotelBookingFilterBO {
    String minAmount;
    String maxAmount;
    String rating;
    String hotelToBook;
    String roomType;
    private List<String> roomOccupants;




}
