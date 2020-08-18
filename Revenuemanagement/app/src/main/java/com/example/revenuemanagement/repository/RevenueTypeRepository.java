package com.example.revenuemanagement.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.revenuemanagement.dao.Appdatabase;
import com.example.revenuemanagement.dao.RevenueTypeDao;
import com.example.revenuemanagement.entity.RevenueType;

import java.util.List;

public class RevenueTypeRepository {
    private RevenueTypeDao revenueTypeDao;
    private LiveData<List<RevenueType>> mRevenueType;

    public RevenueTypeRepository(Application application) {
        this.revenueTypeDao = Appdatabase.getDatabase(application).revenueTypeDao();
        mRevenueType = revenueTypeDao.getAll();
    }

    public LiveData<List<RevenueType>> getRevenueType() {
        return mRevenueType;
    }

    public void insert(RevenueType revenueType){
        new InsertAsycnTask(revenueTypeDao).execute(revenueType);
    }
    public void delete(RevenueType revenueType){
        new DeleteAsycnTask(revenueTypeDao).execute(revenueType);
    }
    public void update(RevenueType revenueType){
        System.out.println("This is update RevenueType Repontory");
        new UpdateAsycnTask(revenueTypeDao).execute(revenueType);
    }

    public static  class InsertAsycnTask extends AsyncTask<RevenueType,Void,Void>{
        RevenueTypeDao revenueTypeDao;
        InsertAsycnTask(RevenueTypeDao revenueTypeDao){
            this.revenueTypeDao = revenueTypeDao;
        }
        @Override
        protected Void doInBackground(RevenueType... revenueTypes) {
            revenueTypeDao.insert(revenueTypes[0]);
            return null;
        }
    }

    public static  class UpdateAsycnTask extends AsyncTask<RevenueType,Void,Void>{
        RevenueTypeDao revenueTypeDao;
        UpdateAsycnTask(RevenueTypeDao revenueTypeDao){
            this.revenueTypeDao = revenueTypeDao;
        }
        @Override
        protected Void doInBackground(RevenueType... revenueTypes) {
            revenueTypeDao.update(revenueTypes[0]);
            return null;
        }
    }

    public static  class DeleteAsycnTask extends AsyncTask<RevenueType,Void,Void>{
        RevenueTypeDao revenueTypeDao;
        DeleteAsycnTask(RevenueTypeDao revenueTypeDao){
            this.revenueTypeDao = revenueTypeDao;
        }
        @Override
        protected Void doInBackground(RevenueType... revenueTypes) {
            revenueTypeDao.delete(revenueTypes[0]);
            return null;
        }
    }

}
