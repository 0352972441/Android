package com.example.android.dao;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.R;
import com.example.android.adapter.Card_Course_Adapter;
import com.example.android.adapter.Card_Course_Register_Adapter;
import com.example.android.dialog.AlertDialogCourse;
import com.example.android.entity.CourseEntity;
import com.example.android.listener.Listener;
import com.example.android.responsive.CourseResponsive;

import java.util.List;

public class CourseRegistrationDao extends AsyncTask<List<CourseEntity>,List<CourseEntity>,Void> {
    private RecyclerView card_course_register_recycler;
    private Context context;
    private static Card_Course_Register_Adapter card_course_register_adapter;
    private CourseResponsive responsive;
    private static List<CourseEntity > courseEntities;
    public CourseRegistrationDao(RecyclerView card_course_register_recycler, Context context,CourseResponsive responsive) {
        this.card_course_register_recycler = card_course_register_recycler;
        this.context = context;
        this.responsive = responsive;
    }

    @SuppressLint("WrongThread")
    @Override
    protected Void doInBackground(List<CourseEntity>... lists) {
        onProgressUpdate(lists[0]);
        return null;
    }

    @Override
    protected void onProgressUpdate(List<CourseEntity>... values) {
        if(values[0] != null){
            courseEntities = values[0];
            card_course_register_adapter = new Card_Course_Register_Adapter(courseEntities);
            card_course_register_recycler.setAdapter(card_course_register_adapter);
            card_course_register_recycler.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
            onClickCardCourse();
        }
    }

    public void onClickCardCourse(){
        card_course_register_adapter.setOnCLickCardCourseListener(new Listener() {
            @Override
            public void lintener(final int position) {
                View view = LayoutInflater.from(context).inflate(R.layout.layout_button_update_course,null,false);
                final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                final AlertDialog alert = dialog.create();
                view.findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        responsive.delete(card_course_register_adapter.getSingleCourse(position));
                        courseEntities.remove(position);
                        card_course_register_adapter.notifyDataSetChanged();
                        alert.dismiss();
                    }
                });
                view.findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialogCourse(position).showDialog(responsive,context,true,card_course_register_adapter.getSingleCourse(position));
                        alert.dismiss();
                    }
                });
                alert.setView(view);
                alert.show();
            }
        });
    }
    public static void onChangeUpdateListener(CourseEntity courseEntity, int index){
        courseEntities.set(index,courseEntity);
        card_course_register_adapter.notifyDataSetChanged();
    }
    public static void onChangeInsertListener(CourseEntity courseEntity){
        courseEntities.add(courseEntity);
        card_course_register_adapter.notifyDataSetChanged();
    }
    
    public void onLickRegister(){
        card_course_register_adapter.setOnClickRegister(new Listener() {
            @Override
            public void lintener(int position) {
                
            }
        });
    }
}
