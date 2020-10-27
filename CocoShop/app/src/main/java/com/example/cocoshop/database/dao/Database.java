package com.example.cocoshop.database.dao;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.cocoshop.database.entity.TopicProgressEntity;

import kotlin.jvm.Synchronized;

@androidx.room.Database(entities = {TopicProgressEntity.class},version = 1)
public abstract class Database extends RoomDatabase {
    public abstract TopicProgressDao topicProgressDao();
    private static Database INSTANCE;
    public static Database getInstance(Context context){
        if(INSTANCE == null){
            synchronized (Database.class){
                INSTANCE = Room.databaseBuilder(context,Database.class,"progress.db").fallbackToDestructiveMigration().allowMainThreadQueries().build();
            }
        }
        return INSTANCE;
    }
}
