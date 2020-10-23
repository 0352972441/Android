package com.example.cocoshop.adapter;

import ir.mirrajabi.searchdialog.core.Searchable;

public class SearchAdapter implements Searchable {
    private String title;

    public SearchAdapter(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
