package com.example.cocoshop.ui.Learnbytopic;

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
import com.example.cocoshop.R;
import com.example.cocoshop.Screen.HomeScreen.HomeScreen;
import com.example.cocoshop.Screen.topicsscreen.LearningTopicActivity;
import com.example.cocoshop.dao.audiodao.TopicDao;
import com.example.cocoshop.listener.Listener;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLearningTopic extends Fragment {
    private RecyclerView cardItemTopic;
    private CardItemTopicAdapter cardAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        cardItemTopic = (RecyclerView)view.findViewById(R.id.cardTopic);
        cardAdapter = new CardItemTopicAdapter(TopicDao.topics);
        cardItemTopic.setAdapter(cardAdapter);
        cardItemTopic.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        cardAdapter.setListenerItem(new Listener() {
            @Override
            public void listener(int position) {
                Intent intent = new Intent(getContext(), LearningTopicActivity.class);
                intent.putExtra(LearningTopicActivity.KEYPOSITION,position);
                startActivity(intent);
            }
        });
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

}
