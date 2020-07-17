package com.example.overloading;

public class CurrentAddress {
    private String address;
    private String description;

    public static final CurrentAddress[] currentAddresses = {
            new CurrentAddress("Alaska","It place center of the city\n Very quite, prefer"),
            new CurrentAddress("Hawai","Very beutifuly. Please, go to play "),
            new CurrentAddress("Amalay","NO NO I don't like noise")
    };

    CurrentAddress(String address, String description){
        this.address = address;
        this.description = description;
    }

    public String getAddress(){
        return address;
    }

    public String getDescription(){
        return description;
    }

    public String toString(){
        return address;
    }
}
