package com.example.cocoshop.Models.audiomodels;

import android.net.Uri;

public class Sound {
    private String title;
    private String urlSound;

    public Sound(String title, String urlSound) {
        this.title = title;
        this.urlSound = urlSound;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlSound() {
        return urlSound;
    }

    public void setUrlSound(String urlSound) {
        this.urlSound = urlSound;
    }
}
