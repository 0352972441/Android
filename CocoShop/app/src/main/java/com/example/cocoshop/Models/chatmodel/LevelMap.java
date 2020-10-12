package com.example.cocoshop.Models.chatmodel;

public class LevelMap {
    private String lesson;
    private int background;
    private Star star;

    public LevelMap(String lesson, int background, Star star) {
        this.lesson = lesson;
        this.background = background;
        this.star = star;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public Star getStar() {
        return star;
    }

    public void setStar(Star star) {
        this.star = star;
    }
}
