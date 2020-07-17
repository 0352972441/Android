package com.example.note.Data;

import android.provider.ContactsContract;

public class Note {
    private String title;
    private int gridImage;

    public Note(String title, int gridImage) {
        this.title = title;
        this.gridImage = gridImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getGridImage() {
        return gridImage;
    }

    public void setGridImage(int gridImage) {
        this.gridImage = gridImage;
    }

    public String toString(){
        return title;
    }
}
