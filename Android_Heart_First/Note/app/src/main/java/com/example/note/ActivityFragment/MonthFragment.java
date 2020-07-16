package com.example.note.ActivityFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.note.Data.CustomAdapter;
import com.example.note.Listenner.Listener;
import com.example.note.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MonthFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_month, container, false);
        ListView listView = (ListView) view.findViewById(R.id.list);
        CustomAdapter adapter = new CustomAdapter(getResources().getStringArray(R.array.array),getActivity(),R.drawable.book);
        listView.setAdapter(adapter);
        Listener listener = new Listener();
        listView.setOnItemClickListener(listener);
        return view;
    }
}
