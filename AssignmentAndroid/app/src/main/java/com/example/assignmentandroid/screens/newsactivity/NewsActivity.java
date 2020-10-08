package com.example.assignmentandroid.screens.newsactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.assignmentandroid.R;
import com.example.assignmentandroid.dao.FetchNewsTask;

public class NewsActivity extends AppCompatActivity {
    private ListView list_item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        list_item = (ListView)findViewById(R.id.list_item);
        new FetchNewsTask(list_item,this).execute();
    }
}
