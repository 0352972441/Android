package com.example.cocoshop.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.example.cocoshop.R;
import com.example.cocoshop.screen.HomeScreen;
import com.example.cocoshop.dao.topicdao.TopicDao;

public class TopicFragment extends Fragment {
    public static TopicDao dao;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        dao = new TopicDao(getActivity());
        dao.execute();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_learning_topic, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        HomeScreen.isCurrentFragment = "TOPIC";
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        TopicDao.countPopular = 1;
    }
}
