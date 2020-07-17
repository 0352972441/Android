package com.example.workout;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
/*
    @Override
    public void itemClicked(long id) {
        Intent intent = new Intent(this, ActivityDetail.class);
        intent.putExtra(ActivityDetail.EXTRA,(int)id);
        startActivity(intent);
    }*/
}
