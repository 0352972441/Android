package com.example.revenuemanagement.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ExpenditureType {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "description")
    private String description;
    /*@ColumnInfo(name = "date")
    private String date;*/
    /*@ColumnInfo(name = "category")
    private String category;*/
    @ColumnInfo(name = "imgCategory")
    private int imgCategory;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

   /* public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }*/

    public int getImgCategory() {
        return imgCategory;
    }

    public void setImgCategory(int imgCategory) {
        this.imgCategory = imgCategory;
    }
}
