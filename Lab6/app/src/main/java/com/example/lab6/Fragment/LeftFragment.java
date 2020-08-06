package com.example.lab6.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.lab6.R;


public class LeftFragment extends Fragment {


    public LeftFragment() {

    }


    public static LeftFragment newInstance(String param1, String param2) {
        LeftFragment fragment = new LeftFragment();
        return fragment;
    }

  /*  @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_left, container, false);
        final EditText message = (EditText)view.findViewById(R.id.message);
        Button showMessage = (Button)view.findViewById(R.id.show_bt);
        showMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                RightFragment rightFragment = (RightFragment) manager.findFragmentById(R.id.rightFragment);
                rightFragment.text.setText(message.getText().toString());
            }
        });
        return view;
    }
}
