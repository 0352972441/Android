package com.example.note.Listenner;

import android.content.Intent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.note.Activity.ContextStory;
import com.example.note.Data.Book;
import com.example.note.R;

import java.util.ArrayList;
import java.util.HashMap;

public class Listener implements AdapterView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<Integer,String> story = new HashMap<>();
        story.put(1,view.getResources().getString(R.string.chap1));
        story.put(2,view.getResources().getString(R.string.chap2));
        Book book = new Book("Cha Giầu cha nghèo",story);
        book.addBook(book);
        Toast toast=Toast.makeText(view.getContext(),book.getTitle(),Toast.LENGTH_LONG);
        toast.show();
        Intent intent = new Intent(view.getContext(), ContextStory.class);
        intent.putExtra(ContextStory.EXTRA,position);
        view.getContext().startActivity(intent);
    }
}
