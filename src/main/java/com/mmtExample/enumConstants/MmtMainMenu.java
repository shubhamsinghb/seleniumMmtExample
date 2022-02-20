package com.mmtExample.enumConstants;

public enum MmtMainMenu {
    FLIGHT("Flights") , HOTEL("Hotels") , HOMESTAYS("Homestays");
    private String value;

    MmtMainMenu(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
