package com.example.assignmentandroid.ui.coursefragment;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignmentandroid.R;
import com.example.assignmentandroid.screens.CourseActivity;

public class MyCourseFragment extends Fragment {

    private MyCourseViewModel mViewModel;

    public static MyCourseFragment newInstance() {
        return new MyCourseFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_course_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MyCourseViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        CourseActivity.isFragmentCurrent = "mycourse";
    }
}
