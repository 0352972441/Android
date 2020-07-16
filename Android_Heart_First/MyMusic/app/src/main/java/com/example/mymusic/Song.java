package com.example.mymusic;

public class Song {
    private String title;
    private String description;
    public static final Song[] ListSong = {
            new Song("EMD", "How long\nWhy not me\n Best tow"),
            new Song("ROCK", "How the world\nSee you are again\nBest Friend"),
            new Song("HipHop", "Betray\nMemory\nI one call")
    };

    public Song(String title, String description){
        this.title = title;
        this.description = description;
    }

    public String getTitle(){
        return  getTitle();
    }
    public String getDescription(){
        return getDescription();
    }
    public String ToString(){
        return  title;
    }
}
