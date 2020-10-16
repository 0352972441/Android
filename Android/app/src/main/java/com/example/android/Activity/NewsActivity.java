package com.example.android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.android.R;
import com.example.android.dao.FetchNewsTask;

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
