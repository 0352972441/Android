package com.example.assignmentandroid.ui.testschedulefragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignmentandroid.R;
import com.example.assignmentandroid.screens.CourseActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestScheduleFragment extends Fragment {

    public TestScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test_schedule, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        CourseActivity.isFragmentCurrent = "testschedule";
    }
}
