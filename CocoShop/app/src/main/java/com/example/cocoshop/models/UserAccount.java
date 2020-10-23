package com.example.cocoshop.models;

public class UserAccount {
    private String account;
    private String email;
    private String image;
    private String uid;

    public UserAccount(String account, String email, String image,String uid) {
        this.account = account;
        this.email = email;
        this.image = image;
        this.uid = uid;
    }

    public String getAccount() {
        return account;
    }

    public String getEmail() {
        return email;
    }

    public String getImage() {
        return image;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
