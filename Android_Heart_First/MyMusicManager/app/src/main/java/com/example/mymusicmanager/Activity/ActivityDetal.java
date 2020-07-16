package com.example.mymusicmanager.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mymusicmanager.ActivityFragment.FragmentDetail;
import com.example.mymusicmanager.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityDetal extends AppCompatActivity {
    public static final String EXTRA = "key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detal);
        sendworkid();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void sendworkid(){
        Intent intent = getIntent();
        int index = (int) intent.getExtras().getInt(EXTRA);
        FragmentDetail fragmentDetail = (FragmentDetail) getSupportFragmentManager().findFragmentById(R.id.fragmetndetal);
        fragmentDetail.getWordId(index);
    }

}
