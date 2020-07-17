package com.example.myswipe.SetUpLayout;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;


public class SetListFragment {
    public static ArrayAdapter setListView(Context context, String[] list){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,list);
        return adapter;
    }
}
