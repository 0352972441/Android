package com.example.lab6.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lab6.R;


public class RightFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";



    private String mParam1;
    TextView text;

    public RightFragment() {

    }


    public static RightFragment newInstance(String param1) {
        RightFragment fragment = new RightFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_right, container, false);
        text = (TextView) view.findViewById(R.id.text);
        text.setText(mParam1);
        return view;
    }
}
