package com.example.assignmentandroid.dao;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.assignmentandroid.R;
import com.example.assignmentandroid.entity.CourseEntity;
import com.example.assignmentandroid.entity.MyCourseEntity;
import com.example.assignmentandroid.models.Rate;

import java.util.ArrayList;

@Database(entities = {CourseEntity.class, MyCourseEntity.class},version = 2)
public abstract class Appdatabase extends RoomDatabase {
    public abstract CourseDao courseDao();
    public abstract MyCourseDao myCourseDao();
    private static Appdatabase INSTANCE;
    private static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            new InitAsyncTask(INSTANCE).execute();
        }
    };
    public static Appdatabase getINSTANCE(final Context context){
        if(INSTANCE == null){
            synchronized (Appdatabase.class){
                INSTANCE = Room.databaseBuilder(context, Appdatabase.class,"course_db").allowMainThreadQueries().fallbackToDestructiveMigration().addCallback(callback).build();
            }
        }
        return INSTANCE;
    }

    static class InitAsyncTask extends AsyncTask<Void,Void,Void>{
        CourseDao dao;

        public InitAsyncTask(Appdatabase dao) {
            this.dao = dao.courseDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ArrayList<CourseEntity> data = new ArrayList<>();
            data.add(new CourseEntity("Java advanced","Jonh", Rate.EXCELLENT.toString(), R.drawable.java));
            data.add(new CourseEntity("Flutter advanced","Jeny", Rate.EXCELLENT.toString(), R.drawable.flutter));
            data.add(new CourseEntity("SQL advanced","Math", Rate.NORMAL.toString(), R.drawable.sql));
            data.add(new CourseEntity("HTML advanced","Thomas", Rate.BAD.toString(), R.drawable.html));
            data.add(new CourseEntity("JS advanced","Avanter", Rate.EXCELLENT.toString(), R.drawable.java));
            data.add(new CourseEntity("CS3 advanced","DeelBus", Rate.VERYGOOD.toString(), R.drawable.css_3));
            for(CourseEntity i : data){
                dao.insert(i);
            }
            return null;
        }
    }

}
