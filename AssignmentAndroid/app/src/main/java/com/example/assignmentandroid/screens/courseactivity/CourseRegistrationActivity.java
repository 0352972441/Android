package com.example.assignmentandroid.screens.courseactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;

import com.example.assignmentandroid.R;
import com.example.assignmentandroid.adapter.Card_Course_Register_Adapter;
import com.example.assignmentandroid.models.CourseRegister;
import com.example.assignmentandroid.models.Rate;

import java.util.ArrayList;

public class CourseRegistrationActivity extends AppCompatActivity {
    private RecyclerView card_course_register_recycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_registration);
        card_course_register_recycler = (RecyclerView)findViewById(R.id.card_course_register);
        Card_Course_Register_Adapter activity_course_registration = new Card_Course_Register_Adapter(data());
        card_course_register_recycler.setAdapter(activity_course_registration);
        card_course_register_recycler.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
    }

    private ArrayList<CourseRegister> data(){
        ArrayList<CourseRegister> data = new ArrayList<>();
        data.add(new CourseRegister("Java advanced","Jonh", Rate.EXCELLENT, R.drawable.java));
        data.add(new CourseRegister("Flutter advanced","Jeny", Rate.GOOD, R.drawable.flutter));
        data.add(new CourseRegister("SQL advanced","Math", Rate.NORMAL, R.drawable.sql));
        data.add(new CourseRegister("HTML advanced","Thomas", Rate.BAD, R.drawable.html));
        data.add(new CourseRegister("JS advanced","Avanter", Rate.EXCELLENT, R.drawable.java));
        data.add(new CourseRegister("CS3 advanced","DeelBus", Rate.VERYGOOD, R.drawable.css_3));
        return data;
    }
}
