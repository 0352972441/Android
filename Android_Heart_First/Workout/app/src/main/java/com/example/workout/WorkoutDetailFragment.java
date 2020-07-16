package com.example.workout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class WorkoutDetailFragment extends Fragment {
    private long workid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
    }

    public void onStart(){
        super.onStart();
        View view = getView();
        if(view != null){
            TextView title = (TextView) view.findViewById(R.id.title);
            TextView description = (TextView) view.findViewById(R.id.description);
            Workout workout = Workout.workout[(int)workid];
            title.setText(workout.getTitle());
            description.setText(workout.getDescription());
        }
    }

    public void setWorkid(long id){
        workid = id;
    }
}
