package com.example.android.models;
import android.widget.ImageView;

public class MainItem {

    private String title;
    private int image;

    public MainItem(String title, int image) {
        this.title = title;
        this.image = image;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
