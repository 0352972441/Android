package com.example.cocoshop.models;

public class Card {
    private int logo;
    private String title;
    private String countCard;
    private int background;

    public Card(int logo, String title, String countCard, int background) {
        this.logo = logo;
        this.title = title;
        this.countCard = countCard;
        this.background = background;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountCard() {
        return countCard;
    }

    public void setCountCard(String countCard) {
        this.countCard = countCard;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }
}
