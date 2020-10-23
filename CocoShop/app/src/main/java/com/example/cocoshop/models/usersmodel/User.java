package com.example.cocoshop.models.usersmodel;

public class User {
    private String kindAccount;
    private String email;
    private String idToken;
    private String Uid;

    public User(String kindAccount, String email, String idToken, String uid) {
        this.kindAccount = kindAccount;
        this.email = email;
        this.idToken = idToken;
        Uid = uid;
    }

    public String getKindAccount() {
        return kindAccount;
    }

    public void setKindAccount(String kindAccount) {
        this.kindAccount = kindAccount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }
}
