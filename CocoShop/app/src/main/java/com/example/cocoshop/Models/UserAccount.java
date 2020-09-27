package com.example.cocoshop.Models;

public class UserAccount {
    private String account;
    private String email;
    private String image;

    public UserAccount(String account, String email, String image) {
        this.account = account;
        this.email = email;
        this.image = image;
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
}
