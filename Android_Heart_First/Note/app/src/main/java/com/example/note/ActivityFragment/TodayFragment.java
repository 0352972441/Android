package com.example.note.ActivityFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.note.Data.ListViewAdapter;
import com.example.note.Listenner.Listener;
import com.example.note.R;


public class TodayFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today, container, false);
        ListView listView = view.findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1
        ,getResources().getStringArray(R.array.array));
        listView.setAdapter(adapter);
//        ListViewAdapter.ListOptions(listView,getResources().getStringArray(R.array.listToday),getActivity());
        Listener listener = new Listener();
        listView.setOnItemClickListener(listener);
        return view;
    }


}
