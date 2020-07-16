package com.example.myswipe.Data;

import java.util.ArrayList;

public class Pizza {
    private String name;
    private int image;
    public  static ArrayList<Pizza> listData = new ArrayList<>(); ;
    public Pizza(String name, int image) {
        this.name = name;
        this.image = image;

    }

    public static void setListData(ArrayList<Pizza> listData) {
        Pizza.listData = listData;
    }

    public String getName() {
        return name;
    }


    public int getImage() {
        return image;
    }

}
