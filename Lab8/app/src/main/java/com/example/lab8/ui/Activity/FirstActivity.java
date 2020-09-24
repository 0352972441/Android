package com.example.lab8.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.lab8.Component.CustomCardView;
import com.example.lab8.Models.Scenery;
import com.example.lab8.R;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.mRecycle);
        CustomCardView adapterCardView = new CustomCardView(data());
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapterCardView);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private ArrayList<Scenery> data(){
        ArrayList<Scenery> data = new ArrayList<>();
        data.add(new Scenery("Ảnh cảnh",R.drawable.im));
        data.add(new Scenery("Ảnh cảnh",R.drawable.im));
        data.add(new Scenery("Ảnh cảnh",R.drawable.im));
        data.add(new Scenery("Ảnh cảnh",R.drawable.im));
        data.add(new Scenery("Ảnh cảnh",R.drawable.im));
        data.add(new Scenery("Ảnh cảnh",R.drawable.im));
       /* data.add(new Scenery("Ảnh hoa",R.drawable.c));
        data.add(new Scenery("Ảnh hoa",R.drawable.j));
        data.add(new Scenery("Ảnh hoa",R.drawable.k));
        data.add(new Scenery("Ảnh hoa",R.drawable.m));*/
        return data;
    }
}
