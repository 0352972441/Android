package com.example.lab3.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.example.lab3.CustomListView.Custom_BaseAdapter_ListView;
import com.example.lab3.Models.Actor;
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
//        String[] fromData = {"country", "icon"};
//        int[] toData = {R.id.country, R.id.icon};
//
//        SimpleAdapter adapter = new SimpleAdapter(this, createDataList(), R.layout.custom_view_item_spinner, fromData, toData);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Custom_BaseAdapter_ListView adapter = new Custom_BaseAdapter_ListView(this, createListData());
        mSpinnerItem.setAdapter(adapter);
    }

    private List<HashMap<String, Object>> createDataList(){
        List<HashMap<String , Object>> data = new ArrayList<>();
        HashMap<String , Object> dataItem1 = new HashMap<>();
        dataItem1.put("country","Facebook");
        dataItem1.put("icon", R.drawable.facebook);
        HashMap<String , Object> dataItem2 = new HashMap<>();
        dataItem2.put("country","Firefox");
        dataItem2.put("icon", R.drawable.firefox);
        HashMap<String , Object> dataItem3 = new HashMap<>();
        dataItem3.put("country","Chrome");
        dataItem3.put("icon",  R.drawable.chrome);
        HashMap<String , Object> dataItem4 = new HashMap<>();
        dataItem4.put("country","Microsoft");
        dataItem4.put("icon", R.drawable.microsoft);
        data.add(dataItem1);
        data.add(dataItem2);
        data.add(dataItem3);
        data.add(dataItem4);
        return data;
    }
    private ArrayList<Actor> createListData(){
        ArrayList<Actor> data = new ArrayList<Actor>();
        data.add(new Actor("Facebook", R.drawable.facebook));
        data.add(new Actor("Firefox",  R.drawable.firefox));
        data.add(new Actor("Chrome",  R.drawable.chrome));
        data.add(new Actor("Microsoft",  R.drawable.microsoft));
        data.add(new Actor("Dell",  R.drawable.dell));
        return data;
    }
}
