package com.example.cocoshop.ui.Learnbytopic;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cocoshop.Adapter.CardItemTopicAdapter;
import com.example.cocoshop.Models.topicsmodel.Levels;
import com.example.cocoshop.Models.topicsmodel.Topic;
import com.example.cocoshop.R;
import com.example.cocoshop.Screen.HomeScreen.HomeScreen;

import java.util.ArrayList;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLearningTopic extends Fragment {
    private RecyclerView cardItemTopic;
    private CardItemTopicAdapter cardAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        cardItemTopic = (RecyclerView)view.findViewById(R.id.cardTopic);
        cardAdapter = new CardItemTopicAdapter(data());
        cardItemTopic.setAdapter(cardAdapter);
        cardItemTopic.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
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

    private ArrayList data(){
        ArrayList<Topic> data = new ArrayList<>();
        data.add(new Topic("School",null, 1, Levels.BEGINER));
        data.add(new Topic("Hello",null, 1, Levels.BEGINER));
        data.add(new Topic("Nice to meet you!",null, 1, Levels.ADVANCED));
        data.add(new Topic("What is your name?",null, 1, Levels.LOWINTERMADIATE));
        data.add(new Topic("Where do you live?",null, 1, Levels.INTERMADIATE));
        return data;
    }

}
