package com.example.cocoshop.Models;

public class User {
    private static String email;
    private static String kind;

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        User.email = email;
    }

    public static String getKind() {
        return kind;
    }

    public static void setKind(String kind) {
        User.kind = kind;
    }

}
