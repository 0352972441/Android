package com.example.mymusicmanager.Data;

public class Song {
    private String title;
    private String description;
    public static final Song[] ListSong = {
            new Song("EMD", "How long\nWhy not me\n Best tow"),
            new Song("ROCK", "How the world\nSee you are again\nBest Friend"),
            new Song("HipHop", "Betray\nMemory\nI one call")
    };

    public Song(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
