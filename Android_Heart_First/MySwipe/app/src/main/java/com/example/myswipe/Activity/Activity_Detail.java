package com.example.myswipe.Activity;
;
import com.example.myswipe.Data.Pizza;
import com.example.myswipe.R;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Activity_Detail extends AppCompatActivity  {
    public final static String EXTRA = "key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__detail);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setUpDetail();
    }


    public  void setUpDetail(){
        Intent intent = getIntent();
        int position = getIntent().getExtras().getInt(EXTRA);
        Pizza listData = Pizza.listData.get(position);
        TextView textView = (TextView)findViewById(R.id.text_info);
        ImageView imageView = (ImageView)findViewById(R.id.image_info);
        textView.setText(listData.getName());
        imageView.setContentDescription(listData.getName());
        imageView.setImageResource(listData.getImage());
    }
}
