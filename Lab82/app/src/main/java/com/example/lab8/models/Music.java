package com.example.lab8.models;

import androidx.annotation.NonNull;

public class Music {
    private String displayName;
    private int sound;

    public Music(String displayName, int sound) {
        this.displayName = displayName;
        this.sound = sound;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getSound() {
        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    @NonNull
    @Override
    public String toString() {
        return displayName;
    }
}
