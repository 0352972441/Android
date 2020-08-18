package com.example.revenuemanagement.Models;

public class Category {
    final String title;
    final int image;

    public Category(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }
}
