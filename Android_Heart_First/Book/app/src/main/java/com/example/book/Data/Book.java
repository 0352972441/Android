package com.example.book.Data;

import java.util.ArrayList;

public class Book {
    private int title;
    private int image;
    private static ArrayList<Book> ListDataBook= new ArrayList<>();
    public Book(int title, int image) {
        this.title = title;
        this.image = image;
    }

    public int getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }

    public static ArrayList<Book> getListDataBook() {
        return ListDataBook;
    }

    public static void setListDataBook(ArrayList<Book> listDataBook) {
        ListDataBook = listDataBook;
    }
}
