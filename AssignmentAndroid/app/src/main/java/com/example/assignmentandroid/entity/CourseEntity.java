package com.example.assignmentandroid.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CourseEntity {

    @PrimaryKey(autoGenerate =  true)
    private int id;
    @ColumnInfo
    private String name;

    public CourseEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CourseEntity() {

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
}
