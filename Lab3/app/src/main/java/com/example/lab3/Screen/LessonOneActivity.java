package com.example.lab3.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.lab3.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LessonOneActivity extends AppCompatActivity {
    ListView mListItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_one);
        mListItem = (ListView)findViewById(R.id.list_item);
        createSimpleListView();
    }

    private void createSimpleListView(){
        String[] fromData = {"name", "age", "image"};
        int[] toData = {R.id.name, R.id.age, R.id.image};
        List<HashMap<String , Object>> data = new ArrayList<>();
        data.add(createDataList("Shank", 28, R.drawable.shank));
        data.add(createDataList("Hancock", 18, R.drawable.hancock));
        data.add(createDataList("One65", 21, R.drawable.one65));
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.custom_view, fromData, toData);
        mListItem.setAdapter(adapter);
    }

    private HashMap createDataList(String name, int age, int image){
        HashMap<String , Object> data = new HashMap<>();
        data.put("name",name);
        data.put("age", age);
        data.put("image", image);
        return data;
    }

}
