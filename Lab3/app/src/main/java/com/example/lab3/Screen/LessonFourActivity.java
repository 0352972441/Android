package com.example.lab3.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lab3.CustomListView.Custom_BaseAdapter_ListView;
import com.example.lab3.Models.Actor;
import com.example.lab3.R;

import java.util.ArrayList;

public class LessonFourActivity extends AppCompatActivity {
    ListView mListItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_four);
        mListItem = (ListView)findViewById(R.id.list_item);
        createListView();
    }

    private void createListView(){
        final ArrayList<Actor> listData = createListData();
        Custom_BaseAdapter_ListView adapter = new Custom_BaseAdapter_ListView(this, listData);
        mListItem.setAdapter(adapter);
        mListItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(LessonFourActivity.this, "Click "+listData.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<Actor> createListData(){
        ArrayList data = new ArrayList<>();
        data.add(new Actor("Facebook", R.drawable.facebook));
        data.add(new Actor("Firefox",  R.drawable.firefox));
        data.add(new Actor("Chrome",  R.drawable.chrome));
        data.add(new Actor("Microsoft",  R.drawable.microsoft));
        data.add(new Actor("Apple",  R.drawable.apple));
        data.add(new Actor("Hp",  R.drawable.hp));
        data.add(new Actor("Blogger",  R.drawable.blogger));
        data.add(new Actor("Android",  R.drawable.android));
        data.add(new Actor("Kimsibeun",  R.drawable.kimsoeun));
        data.add(new Actor("Kimnajoo",  R.drawable.kimnamjoo));
        data.add(new Actor("Hancock",  R.drawable.hancock));
        data.add(new Actor("Kimsoeeun",  R.drawable.kimsoeun));
        data.add(new Actor("Kimtaehee",  R.drawable.kimtaehee));
        data.add(new Actor("Apple",  R.drawable.apple));
        data.add(new Actor("Dell",  R.drawable.dell));
        return data;
    }



}
