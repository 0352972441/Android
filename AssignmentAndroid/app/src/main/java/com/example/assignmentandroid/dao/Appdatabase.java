package com.example.assignmentandroid.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.assignmentandroid.entity.CourseEntity;

@Database(entities = {CourseEntity.class},version = 1)
public abstract class Appdatabase extends RoomDatabase {
    public abstract CourseDao courseDao();
    private static Appdatabase INSTANCE;

    public static Appdatabase getINSTANCE(final Context context){
        if(INSTANCE == null){
            synchronized (Appdatabase.class){
                INSTANCE = Room.databaseBuilder(context, Appdatabase.class,"course_db").build();
            }
        }
        return INSTANCE;
    }
}
