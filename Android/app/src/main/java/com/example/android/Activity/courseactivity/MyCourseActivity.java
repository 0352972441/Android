package com.example.android.Activity.courseactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.android.R;
import com.example.android.adapter.Card_My_Course_Adapter;
import com.example.android.responsive.MyCourseResponsive;

public class MyCourseActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMyCourse;
    private MyCourseResponsive responsive;
    private Card_My_Course_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_course);
        responsive = new MyCourseResponsive(this);
        adapter = new Card_My_Course_Adapter(responsive.getMyCourseEntities());
        recyclerViewMyCourse = (RecyclerView) findViewById(R.id.recycler_view_my_course);
        recyclerViewMyCourse.setAdapter(adapter);
        recyclerViewMyCourse.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
    }
}
