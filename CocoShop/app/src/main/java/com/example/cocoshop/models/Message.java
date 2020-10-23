package com.example.cocoshop.models;

public class Message {
    private String message;
    private String avata;
    private String userName;
    private String time;
    private String uid;
    private String accoutType;

    public Message(String message, String avata, String userName, String time, String uid, String accoutType) {
        this.message = message;
        this.avata = avata;
        this.userName = userName;
        this.time = time;
        this.uid = uid;
        this.accoutType = accoutType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAvata() {
        return avata;
    }

    public void setAvata(String avata) {
        this.avata = avata;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAccoutType() {
        return accoutType;
    }

    public void setAccoutType(String accoutType) {
        this.accoutType = accoutType;
    }
}
