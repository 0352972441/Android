package com.example.cocoshop.ui.Progressivelearning;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cocoshop.R;
import com.example.cocoshop.Screen.HomeScreen.HomeScreen;


public class FragmentChat extends Fragment {
    private RecyclerView message;
    private EditText edBoxMessage;
    private ImageView imgSend;
    private ImageView imgrecord;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        message = (RecyclerView)view.findViewById(R.id.message);
        edBoxMessage = (EditText)view.findViewById(R.id.box_message);
        imgSend = (ImageView)view.findViewById(R.id.img_send);
        imgrecord = (ImageView)view.findViewById(R.id.img_record);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        HomeScreen.isCurrentFragment = "Chat";
    }
}
