package com.example.note.ActivityFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.note.Data.ListViewAdapter;
import com.example.note.Listenner.Listener;
import com.example.note.R;


public class WeekFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_week, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listWeek);
        ListViewAdapter.ListOptions(listView,getResources().getStringArray(R.array.array),getActivity());
        Listener listener = new Listener();
        listView.setOnItemClickListener(listener);
        return view;
    }
}
