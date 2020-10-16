package com.example.android.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;

import com.example.android.Activity.courseactivity.CourseActivity;
import com.example.android.R;
import com.example.android.adapter.MainItemAdapter;
import com.example.android.listener.Listener;
import com.example.android.models.MainItem;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerViewItem;
    private MainItemAdapter itemAdapter;
    private static final Object[] listActivity= {
            CourseActivity.class, MapsActivity.class, NewsActivity.class
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerViewItem = (RecyclerView)findViewById(R.id.recycler_item);
        itemAdapter = new MainItemAdapter(data());
        recyclerViewItem.setAdapter(itemAdapter);
        recyclerViewItem.setLayoutManager(new GridLayoutManager(this,2));
        itemAdapter.setListenerMainItem(new Listener() {
            @Override
            public void lintener(int position) {
                Intent intent = new Intent(HomeActivity.this, (Class<?>) listActivity[position]);
                startActivity(intent);
            }
        });
    }

    private ArrayList<MainItem> data(){
        ArrayList<MainItem> items = new ArrayList<>();
        items.add(new MainItem("Course",R.drawable.icon_main_item_course));
        items.add(new MainItem("Maps",R.drawable.icon_main_item_map));
        items.add(new MainItem("News",R.drawable.icon_main_item_news));
        return items;
    }
}
