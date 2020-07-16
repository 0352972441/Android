package com.example.note.Data;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.note.R;

public class ListViewAdapter {
    public static void ListOptions(ListView view,String[] title, Context context){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,title);
        view.setAdapter(adapter);
    }
}
