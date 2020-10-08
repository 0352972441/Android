package com.example.assignmentandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.assignmentandroid.adapter.MainItemAdapter;
import com.example.assignmentandroid.listener.Listener;
import com.example.assignmentandroid.models.MainItem;
import com.example.assignmentandroid.screens.CourseActivity;
import com.example.assignmentandroid.screens.MapsActivity;
import com.example.assignmentandroid.screens.newsactivity.NewsActivity;
import com.example.assignmentandroid.screens.SocialActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewItem;
    private MainItemAdapter itemAdapter;
    private static final Object[] listActivity= {
            CourseActivity.class, MapsActivity.class, NewsActivity.class, SocialActivity.class
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewItem = (RecyclerView)findViewById(R.id.recycler_item);
        itemAdapter = new MainItemAdapter(data());
        recyclerViewItem.setAdapter(itemAdapter);
        recyclerViewItem.setLayoutManager(new GridLayoutManager(this,2));
        itemAdapter.setListenerMainItem(new Listener() {
            @Override
            public void lintener(int position) {
                Intent intent = new Intent(MainActivity.this, (Class<?>) listActivity[position]);
                startActivity(intent);
            }
        });
    }

    private ArrayList<MainItem> data(){
        ArrayList<MainItem> items = new ArrayList<>();
        items.add(new MainItem("Course",R.drawable.icon_main_item_course));
        items.add(new MainItem("Maps",R.drawable.icon_main_item_map));
        items.add(new MainItem("News",R.drawable.icon_main_item_news));
        items.add(new MainItem("Social",R.drawable.icon_main_item_social));
        return items;
    }
}
