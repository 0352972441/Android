package com.example.salebeer;
import java.util.ArrayList;

public class BeerExpert {
    public ArrayList<String> getBrand(String color){
        ArrayList<String> brands = new ArrayList<>();
        if(color.equals("Yellow")){
            brands.add("Yellow orrange");
            brands.add("Yellow Red");
        }else{
            brands.add("Yellow Not");
            brands.add("Yellow Can");
        }
        return brands;
    }
}
