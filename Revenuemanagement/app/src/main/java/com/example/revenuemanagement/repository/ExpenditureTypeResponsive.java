package com.example.revenuemanagement.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.revenuemanagement.dao.Appdatabase;
import com.example.revenuemanagement.dao.ExpenditureTypeDao;
import com.example.revenuemanagement.entity.ExpenditureType;

import java.util.List;

public class ExpenditureTypeResponsive {
    private LiveData<List<ExpenditureType>> getAll;
    private ExpenditureTypeDao expenditureTypeDao;

    public ExpenditureTypeResponsive(Application application) {
        expenditureTypeDao = Appdatabase.getDatabase(application).expenditureTypeDao();
        getAll = expenditureTypeDao.getAll();
    }

    public LiveData<List<ExpenditureType>> getGetAll() {
        return getAll;
    }

    public void insert(ExpenditureType expenditureType){
        new InsertAsyncTask(expenditureTypeDao).execute(expenditureType);
    }

    public void delete(ExpenditureType expenditureType){
        new DeleteAsyncTask(expenditureTypeDao).execute(expenditureType);
    }

    public void update(ExpenditureType expenditureType){
        new UpdateAsyncTask(expenditureTypeDao).execute(expenditureType);
    }

    static class InsertAsyncTask extends AsyncTask<ExpenditureType,Void,Void>{
        private ExpenditureTypeDao expenditureTypeDao;
        public InsertAsyncTask(ExpenditureTypeDao expenditureTypeDao) {
            this.expenditureTypeDao = expenditureTypeDao;
        }

        @Override
        protected Void doInBackground(ExpenditureType... expenditureTypes) {
            expenditureTypeDao.insert(expenditureTypes[0]);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<ExpenditureType,Void,Void>{
        private ExpenditureTypeDao expenditureTypeDao;
        public DeleteAsyncTask(ExpenditureTypeDao expenditureTypeDao) {
            this.expenditureTypeDao = expenditureTypeDao;
        }

        @Override
        protected Void doInBackground(ExpenditureType... expenditureTypes) {
            expenditureTypeDao.delete(expenditureTypes[0]);
            return null;
        }
    }

    static class UpdateAsyncTask extends AsyncTask<ExpenditureType,Void,Void>{
        private ExpenditureTypeDao expenditureTypeDao;
        public UpdateAsyncTask(ExpenditureTypeDao expenditureTypeDao) {
            this.expenditureTypeDao = expenditureTypeDao;
        }

        @Override
        protected Void doInBackground(ExpenditureType... expenditureTypes) {
            expenditureTypeDao.update(expenditureTypes[0]);
            return null;
        }
    }

}
