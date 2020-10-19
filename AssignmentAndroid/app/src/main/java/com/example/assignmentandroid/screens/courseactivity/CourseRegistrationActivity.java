package com.example.assignmentandroid.screens.courseactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.assignmentandroid.R;
import com.example.assignmentandroid.adapter.Card_Course_Register_Adapter;
import com.example.assignmentandroid.entity.CourseEntity;
import com.example.assignmentandroid.models.CourseRegister;
import com.example.assignmentandroid.models.Rate;
import com.example.assignmentandroid.responsive.CourseResponsive;

import java.util.ArrayList;
import java.util.List;

public class CourseRegistrationActivity extends AppCompatActivity {
    private RecyclerView card_course_register_recycler;
    private CourseResponsive courseResponsive;
    private Card_Course_Register_Adapter activity_course_registration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_registration);
        courseResponsive = new CourseResponsive(this);
        card_course_register_recycler = (RecyclerView)findViewById(R.id.card_course_register);
        activity_course_registration = new Card_Course_Register_Adapter(courseResponsive.getGetAll());
        card_course_register_recycler.setAdapter(activity_course_registration);
        card_course_register_recycler.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_course,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add){
            View view = LayoutInflater.from(this).inflate(R.layout.alert_dialog_add_course,null,false);
            final EditText txAuthor = (EditText)view.findViewById(R.id.tx_author);
            final EditText txTitle = (EditText)view.findViewById(R.id.tx_title);
            final ImageView imgCourse = (ImageView)view.findViewById(R.id.img_course);
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setView(view);
            dialog.setTitle("Uploading course");
            dialog.setPositiveButton("Upload", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String author = txAuthor.getText().toString();
                    String title = txTitle.getText().toString();
                    Drawable imageCouse = imgCourse.getDrawable();
                    CourseEntity register = new CourseEntity(title,author,Rate.VERYGOOD.toString(),R.drawable.flutter);
                    courseResponsive.insert(register);
                }
            });
            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
