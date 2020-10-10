package com.example.cocoshop.ui.Learnbytopic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cocoshop.Adapter.topicsadapter.CardItemTopicAdapter;
import com.example.cocoshop.Models.topicsmodel.Topic;
import com.example.cocoshop.R;
import com.example.cocoshop.Screen.HomeScreen.HomeScreen;
import com.example.cocoshop.Screen.topicsscreen.LearningTopicActivity;
import com.example.cocoshop.dao.audiodao.TopicDao;
import com.example.cocoshop.listener.Listener;

import java.util.ArrayList;

public class FragmentLearningTopic extends Fragment {
    private RecyclerView cardItemTopic;
//    private CardItemTopicAdapter cardAdapter;
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
        return inflater.inflate(R.layout.fragment_learning_topic, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        HomeScreen.isCurrentFragment = "TOPIC";
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
