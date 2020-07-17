package com.example.myapplication.ViewModel;

public class MessageViewModel {
    private static String message;
    private static String message1;
    private static String message2;
    private static String title;
    public static final MessageViewModel[] one = {new MessageViewModel(title,message,message1,message2) };
    public MessageViewModel(String title, String message,String message1,String message2){
        this.title = title;
        this.message = message;
        this.message1 = message1;
        this.message2 = message2;
    }

    public static String getMessage1() {
        return message1;
    }

    public static void setMessage1(String message1) {
        MessageViewModel.message1 = message1;
    }

    public static String getMessage2() {
        return message2;
    }

    public static void setMessage2(String message2) {
        MessageViewModel.message2 = message2;
    }

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        MessageViewModel.message = message;
    }

    public static String getTitle() {
        return title;
    }

    public static void setTitle(String title) {
        MessageViewModel.title = title;
    }
}
