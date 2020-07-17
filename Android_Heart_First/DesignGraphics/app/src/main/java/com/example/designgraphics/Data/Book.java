package com.example.designgraphics.Data;

public class Book {
    private String tite;
    private int image;

    public Book(String tite, int image) {
        this.tite = tite;
        this.image = image;
    }

    public String getTite() {
        return tite;
    }

    public int getImage() {
        return image;
    }
}
