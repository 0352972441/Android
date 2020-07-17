package com.example.mymusic;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mymusic.R;
import com.example.mymusic.Song;

public class ListFragments extends ListFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String[] title = new String[Song.ListSong.length];
        for(int i=0; i<title.length; i++){
            title[i] = Song.ListSong[i].getTitle();
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(inflater.getContext(),android.R.layout.simple_list_item_1,title);
        setListAdapter(arrayAdapter);
        return super.onCreateView(inflater,container,savedInstanceState);

    }
}
