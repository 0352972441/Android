package com.example.lab3.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.lab3.CustomListView.Custom_Base_GridView;
import com.example.lab3.Models.Provider;
import com.example.lab3.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LessonThreeActivity extends AppCompatActivity {
    GridView mGridItem = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_three);
        mGridItem = (GridView)findViewById(R.id.grid_item);
        createListGridView();
    }

    private void createListGridView(){
        Custom_Base_GridView base_gridView = new Custom_Base_GridView(this, createListData());
        mGridItem.setAdapter(base_gridView);
    }

    private ArrayList createListData(){
        ArrayList<Provider> data = new ArrayList<>();
        data.add(new Provider("Facebook", R.drawable.facebook));
        data.add(new Provider("FireFox", R.drawable.firefox));
        data.add(new Provider("Microsoft", R.drawable.microsoft));
        data.add(new Provider("Blogger", R.drawable.blogger));
        data.add(new Provider("Dell", R.drawable.dell));
        data.add(new Provider("HP", R.drawable.hp));
        data.add(new Provider("Chrome", R.drawable.chrome));
        data.add(new Provider("Appple", R.drawable.apple));
        return data;
    }

}
