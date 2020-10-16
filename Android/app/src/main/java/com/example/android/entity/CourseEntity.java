package com.example.android.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class CourseEntity {

    @PrimaryKey(autoGenerate =  true)
    private int id;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private int image;
    @ColumnInfo
    private String rate;
    @ColumnInfo
    private String byAuthor;

    public CourseEntity(String name, String byAuthor, String rate,int image) {
        this.name = name;
        this.image = image;
        this.rate = rate;
        this.byAuthor = byAuthor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getByAuthor() {
        return byAuthor;
    }

    public void setByAuthor(String byAuthor) {
        this.byAuthor = byAuthor;
    }
}
