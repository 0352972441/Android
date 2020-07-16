package com.example.workout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ActivityDetail extends AppCompatActivity {
    public static  final String EXTRA = "EXTRA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        WorkoutDetailFragment flag = (WorkoutDetailFragment) getSupportFragmentManager().findFragmentById(R.id.workid);
        Intent intent = getIntent();
        int index = (int)intent.getExtras().get(EXTRA);
        flag.setWorkid(index);
    }
}
