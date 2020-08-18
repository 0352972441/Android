package com.example.revenuemanagement.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.revenuemanagement.dao.Appdatabase;
import com.example.revenuemanagement.dao.RevenueTypeDao;
import com.example.revenuemanagement.dao.RevunueDao;
import com.example.revenuemanagement.entity.Revenue;
import com.example.revenuemanagement.entity.RevenueType;

import java.util.List;

public class RevenueReponsitory {
    RevunueDao revenueDao;
    private LiveData<List<Revenue>> getAll;

    public RevenueReponsitory(Application application){
        revenueDao = Appdatabase.getDatabase(application).revunueDao();
        getAll = revenueDao.getAll();
    }

    public LiveData<List<Revenue>> getGetAll() {
        return getAll;
    }

    public void insert(Revenue revenue){
        new RevenueReponsitory.InsertAsycnTask(revenueDao).execute(revenue);
    }
    public void update(Revenue revenue){
        new UpdateAsyncTask(revenueDao).execute(revenue);
    }
    public void delete(Revenue revenue){
        new DeleteAsyncTask(revenueDao).execute(revenue);
    }

    public static  class InsertAsycnTask extends AsyncTask<Revenue,Void,Void> {
        RevunueDao revenueDao;

        InsertAsycnTask(RevunueDao revenueDao) {
            this.revenueDao = revenueDao;
        }

        @Override
        protected Void doInBackground(Revenue... revenue) {
            revenueDao.insert(revenue[0]);
            return null;
        }
    }

    public static class UpdateAsyncTask extends AsyncTask<Revenue,Void , Void>{
        RevunueDao revunueDao;
        public UpdateAsyncTask(RevunueDao revunueDao) {
            this.revunueDao = revunueDao;
        }

        @Override
        protected Void doInBackground(Revenue... revenues) {
            revunueDao.update(revenues[0]);
            return null;
        }
    }

    public static class DeleteAsyncTask extends AsyncTask<Revenue,Void , Void>{
        RevunueDao revunueDao;
        public DeleteAsyncTask(RevunueDao revunueDao) {
            this.revunueDao = revunueDao;
        }

        @Override
        protected Void doInBackground(Revenue... revenues) {
            revunueDao.delete(revenues[0]);
            return null;
        }
    }
}
