package com.mmtExample.enumConstants;

import lombok.val;

public enum TravellingFor {
    WORK("Work"), LEISURE("Leisure");

    private String value;
    TravellingFor(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
