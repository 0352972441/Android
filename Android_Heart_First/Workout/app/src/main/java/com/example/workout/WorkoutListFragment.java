package com.example.workout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.ListView;


import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutListFragment extends ListFragment {

    static interface Listener{
        public void itemClicked(long id);
    }
    private Listener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] title = new String[Workout.workout.length];
        for(int i=0; i<title.length; i++){
            title[i] = Workout.workout[i].getTitle();
        }
        ArrayAdapter<Workout> arrayAdapter = new ArrayAdapter<>(inflater.getContext(),android.R.layout.simple_list_item_1,Workout.workout);
        setListAdapter(arrayAdapter);
        return super.onCreateView(inflater, container, savedInstanceState);

    }
/*
    public void onAttach(Context context){
        super.onAttach(context);
        this.listener =(Listener)context;
    }

    @Override
    public void onListItemClick(ListView listView,  View v, int position, long id) {
        super.onListItemClick(listView, v, position, id);
        if(listener != null){
            listener.itemClicked(id);
        }
    }*/
}
