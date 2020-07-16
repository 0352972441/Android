package com.example.note.Data;

import java.util.ArrayList;
import java.util.HashMap;

public class Book{
    private String title;
    private HashMap<Integer,String> context;
    public static final ArrayList<Book> listBook = new ArrayList<>();

    public Book() {
    }

    public Book(String title, HashMap<Integer, String> context) {
        this.title = title;
        this.context = context;
    }

    public void addBook(Book book){
        listBook.add(book);
    }


    public String getTitle() {
        return title;
    }

    public HashMap<Integer, String> getContext() {
        return context;
    }
}
