package com.example.android.models;

public class CourseRegister {
    private String title;
    private String byAuthor;
    private Rate rate;
    private int imgCourse;

    public CourseRegister(String title, String byAuthor, Rate rate,int imgCourse) {
        this.title = title;
        this.byAuthor = byAuthor;
        this.rate = rate;
        this.imgCourse = imgCourse;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getByAuthor() {
        return byAuthor;
    }

    public void setByAuthor(String byAuthor) {
        this.byAuthor = byAuthor;
    }

    public Rate getRate() {
        return rate;
    }

    public int getImgCourse() {
        return imgCourse;
    }

    public void setImgCourse(int imgCourse) {
        this.imgCourse = imgCourse;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }
}
