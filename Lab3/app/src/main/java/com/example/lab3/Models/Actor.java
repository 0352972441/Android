package com.example.lab3.Models;

public class Actor {
    private final String name;
    private final int image;

    public Actor(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}
