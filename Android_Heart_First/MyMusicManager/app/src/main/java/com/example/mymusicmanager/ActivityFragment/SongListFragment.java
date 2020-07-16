package com.example.mymusicmanager.ActivityFragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import com.example.mymusicmanager.ListenerFragmenr.Listenner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mymusicmanager.Data.Song;
import com.example.mymusicmanager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongListFragment extends ListFragment {
    private Listenner listenner;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        String[] title = new String[Song.ListSong.length];
        for(int i=0; i<title.length; i++){
            title[i] = Song.ListSong[i].getTitle();
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(inflater.getContext(),android.R.layout.simple_list_item_1, title);
        setListAdapter(arrayAdapter);
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listenner = (Listenner)context;
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if(listenner != null){
            listenner.clickitem(id);
        }
    }
}
