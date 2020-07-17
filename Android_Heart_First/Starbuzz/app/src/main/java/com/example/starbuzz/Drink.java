package com.example.starbuzz;

public class Drink {
    private String name;
    private String describe;
    private int imageId;

    static final Drink[] drinks = {new Drink ("Latte","A couple of espresso shots with steamed milk",R.drawable.latte),
    new Drink("Cappuccino","Espresso, hot milk, and a steamed milk foam",R.drawable.cappuccino),
    new Drink("Filter","Highest quality beans roasted and brewed fresh",R.drawable.filter)};

    public Drink(String name, String describe, int imageId){
        this.describe = describe;
        this.imageId = imageId;
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public String getDescribe(){
        return describe;
    }
    public int getImageId(){
        return imageId;
    }

    public String toString(){
        return name;
    }
}
