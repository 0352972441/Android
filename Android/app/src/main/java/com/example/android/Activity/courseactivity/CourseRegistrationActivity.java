package com.example.android.Activity.courseactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.android.R;
import com.example.android.adapter.Card_Course_Register_Adapter;
import com.example.android.dao.CourseRegistrationDao;
import com.example.android.dialog.AlertDialogCourse;
import com.example.android.entity.CourseEntity;
import com.example.android.models.Rate;
import com.example.android.responsive.CourseResponsive;

public class CourseRegistrationActivity extends AppCompatActivity {

    private RecyclerView card_course_register_recycler;
    private CourseResponsive courseResponsive;
    private CourseRegistrationDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_registration);
        courseResponsive = new CourseResponsive(this);
        card_course_register_recycler = (RecyclerView)findViewById(R.id.card_course_register);
        dao = new CourseRegistrationDao(card_course_register_recycler,this,courseResponsive);
        dao.execute(courseResponsive.getGetAll());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_course,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add){
            new AlertDialogCourse().showDialog(courseResponsive,this,false,null);
            //activity_course_registration.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }
}
