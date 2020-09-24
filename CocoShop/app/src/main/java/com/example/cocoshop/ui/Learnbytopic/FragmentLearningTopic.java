package com.example.cocoshop.ui.Learnbytopic;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cocoshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLearningTopic extends Fragment {

    public FragmentLearningTopic() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learning_topic, container, false);
    }
}
