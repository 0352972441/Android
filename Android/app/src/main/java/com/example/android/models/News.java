package com.example.android.models;

import androidx.annotation.NonNull;

public class News {
    private String title;
    private String link;
    private String desciption;

    public News(String title, String link, String desciption) {
        this.title = title;
        this.link = link;
        this.desciption = desciption;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    @NonNull
    @Override
    public String toString() {
        return title;
    }
}
