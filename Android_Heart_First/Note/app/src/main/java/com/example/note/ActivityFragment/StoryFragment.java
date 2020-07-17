package com.example.note.ActivityFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.note.Data.Book;
import com.example.note.Data.Event;
import com.example.note.R;


public class StoryFragment extends Fragment{

    private int position;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story, container, false);
        // Inflate the layout for this fragment
        setupStory(view);
        return view;
    }

    public void setupStory(View view){
        Event.favorite(view);
        TextView chapOne = (TextView) view.findViewById(R.id.content1);
        TextView chapTwo = (TextView) view.findViewById(R.id.content2);
        TextView titleOne = (TextView) view.findViewById(R.id.chap1);
        TextView titleTwo = (TextView) view.findViewById(R.id.chap2);
        Book book = Book.listBook.get(position);
        chapOne.setText(book.getContext().get(1));
        chapTwo.setText(book.getContext().get(2));
        titleOne.setText(view.getResources().getText(R.string.chracterOne));
        titleTwo.setText(view.getResources().getText(R.string.chracterTwo));
    }


    public void index(int position) {
        this.position = position;
    }
}
