package com.example.lab3.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.example.lab3.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LessonTwoActivity extends AppCompatActivity {
    Spinner mSpinnerItem = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_two);
        mSpinnerItem = (Spinner)findViewById(R.id.spinner_item);
        createSimpleSpinnerView();
    }

    private void createSimpleSpinnerView(){
        String[] fromData = {"name", "image"};
        int[] toData = {R.id.name, R.id.image};
        List<HashMap<String , Object>> data = new ArrayList<>();
        data.add(createDataList("Facebook", R.drawable.facebook));
        data.add(createDataList("Firefox",  R.drawable.firefox));
        data.add(createDataList("Chrome",  R.drawable.chrome));
        data.add(createDataList("Microsoft",  R.drawable.microsoft));
        data.add(createDataList("Dell",  R.drawable.dell));
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.custom_view_item_spinner, fromData, toData);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerItem.setAdapter(adapter);
    }

    private HashMap createDataList(String name,int image){
        HashMap<String , Object> data = new HashMap<>();
        data.put("name",name);
        data.put("image", image);
        return data;
    }
}
