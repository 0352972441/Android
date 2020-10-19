package com.example.assignmentandroid.responsive;

import android.content.Context;
import android.os.AsyncTask;

import com.example.assignmentandroid.dao.Appdatabase;
import com.example.assignmentandroid.dao.MyCourseDao;
import com.example.assignmentandroid.entity.CourseEntity;
import com.example.assignmentandroid.entity.MyCourseEntity;

import java.util.List;

public class MyCourseResponsive {
    private MyCourseDao dao;
    private List<MyCourseEntity> myCourseEntities;

    public MyCourseResponsive(Context context) {
        this.dao = Appdatabase.getINSTANCE(context).myCourseDao();
        myCourseEntities = dao.getAll();
    }

    public List<MyCourseEntity> getMyCourseEntities() {
        return myCourseEntities;
    }

    public void insert(MyCourseEntity entity){
        new MyCourseResponsive.insertAsyncTask(dao).execute(entity);
    }
    static class insertAsyncTask extends AsyncTask<MyCourseEntity, Void, Void> {
        private MyCourseDao myCourseDao;

        insertAsyncTask(MyCourseDao myCourseDao) {
            this.myCourseDao = myCourseDao;
        }

        @Override
        protected Void doInBackground(MyCourseEntity... data) {
            myCourseDao.insert(data[0]);
            return null;
        }
    }

    public void delete(MyCourseEntity entity){
        new MyCourseResponsive.deleteAsyncTask(dao).execute(entity);
    }
    static class deleteAsyncTask extends AsyncTask<MyCourseEntity, Void, Void>{
        private MyCourseDao myCourseDao;

        public deleteAsyncTask(MyCourseDao myCourseDao) {
            this.myCourseDao = myCourseDao;
        }

        @Override
        protected Void doInBackground(MyCourseEntity... data) {
            myCourseDao.delete(data[0]);
            return null;
        }
    }
}
