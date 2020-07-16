package com.example.book.Data;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.book.Activity.Book_Infomation;
import com.example.book.AdapterRecycel.AdapterRecycler;
import com.example.book.ListenerFragment.Listener;
import com.example.book.R;

import java.util.ArrayList;


public class DataInterface{

    public static void createDataRecycler(final View view, final ArrayList<? extends Object> listData,final String table){
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_layout);
        AdapterRecycler adapterRecycler = new AdapterRecycler((ArrayList<Book>) listData);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(),3);
        recyclerView.setAdapter(adapterRecycler);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapterRecycler.setListener(new Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(view.getContext(), Book_Infomation.class);
                intent.putExtra(Book_Infomation.EXTRA,position);
                intent.putExtra(Book_Infomation.EXTRA_TABLE,table);
                view.getContext().startActivity(intent);
            }
        });
    }


}

