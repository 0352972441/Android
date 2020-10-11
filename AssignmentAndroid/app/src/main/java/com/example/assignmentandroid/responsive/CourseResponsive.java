package com.example.assignmentandroid.responsive;

import android.content.Context;
import android.os.AsyncTask;

import com.example.assignmentandroid.dao.Appdatabase;
import com.example.assignmentandroid.dao.CourseDao;
import com.example.assignmentandroid.entity.CourseEntity;

import java.util.List;

public class CourseResponsive {
    private List<CourseEntity> getAll;
    private CourseDao courseDao;
    public CourseResponsive(Context context) {
        courseDao = Appdatabase.getINSTANCE(context).courseDao();
        getAll = courseDao.getAll();
    }

    public List<CourseEntity> getGetAll() {
        return getAll;
    }

    public void insert(CourseEntity course){
        new insertAsyncTask(courseDao).execute(course);
    }
    static class insertAsyncTask extends AsyncTask<CourseEntity, Void, Void>{
        private CourseDao courseDao;

        public insertAsyncTask(CourseDao courseDao) {
            this.courseDao = courseDao;
        }

        @Override
        protected Void doInBackground(CourseEntity... data) {
            courseDao.insert(data[0]);
            return null;
        }
    }

    public void delete(CourseEntity course){
        new deleteAsyncTask(courseDao).execute(course);
    }
    static class deleteAsyncTask extends AsyncTask<CourseEntity, Void, Void>{
        private CourseDao courseDao;

        public deleteAsyncTask(CourseDao courseDao) {
            this.courseDao = courseDao;
        }

        @Override
        protected Void doInBackground(CourseEntity... data) {
            courseDao.delete(data[0]);
            return null;
        }
    }
}
