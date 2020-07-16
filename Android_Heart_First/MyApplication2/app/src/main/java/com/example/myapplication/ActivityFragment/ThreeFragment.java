package com.example.myapplication.ActivityFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeFragment extends ListFragment{

    public ThreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1
                ,getResources().getStringArray(R.array.name));
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
