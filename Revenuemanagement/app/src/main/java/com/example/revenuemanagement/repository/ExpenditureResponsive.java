package com.example.revenuemanagement.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.revenuemanagement.dao.Appdatabase;
import com.example.revenuemanagement.dao.ExpenditureDao;
import com.example.revenuemanagement.entity.Expenditure;

import java.util.List;

public class ExpenditureResponsive {
    private ExpenditureDao expenditureDao;
    private LiveData<List<Expenditure>> getAll;

    public ExpenditureResponsive(Application application) {
        this.expenditureDao = Appdatabase.getDatabase(application).expenditureDao();
        getAll = expenditureDao.getAll();
    }



    public LiveData<List<Expenditure>> getGetAll() {
        return getAll;
    }

    public void insert(Expenditure expenditure){
        new InsertAsysnTask(expenditureDao).execute(expenditure);
    }

    public void update(Expenditure expenditure){
        new UpdateAsysnTask(expenditureDao).execute(expenditure);
    }

    public void delete(Expenditure expenditure){
        new DeleteAsysnTask(expenditureDao).execute(expenditure);
    }

    // New
    public LiveData<List<Expenditure>> getAllPurchases(String category){
        return expenditureDao.getPurchase(category);
    }
    //new

    static class InsertAsysnTask extends AsyncTask<Expenditure,Void , Void>{
        private ExpenditureDao expenditureDao;
        public InsertAsysnTask(ExpenditureDao expenditureDao) {
            this.expenditureDao = expenditureDao;
        }

        @Override
        protected Void doInBackground(Expenditure... expenditures) {
            expenditureDao.insert(expenditures[0]);
            return null;
        }
    }

    static class UpdateAsysnTask extends AsyncTask<Expenditure,Void , Void>{
        private ExpenditureDao expenditureDao;
        public UpdateAsysnTask(ExpenditureDao expenditureDao) {
            this.expenditureDao = expenditureDao;
        }

        @Override
        protected Void doInBackground(Expenditure... expenditures) {
            expenditureDao.update(expenditures[0]);
            return null;
        }
    }

    static class DeleteAsysnTask extends AsyncTask<Expenditure,Void , Void>{
        private ExpenditureDao expenditureDao;
        public DeleteAsysnTask(ExpenditureDao expenditureDao) {
            this.expenditureDao = expenditureDao;
        }

        @Override
        protected Void doInBackground(Expenditure... expenditures) {
            expenditureDao.delete(expenditures[0]);
            return null;
        }
    }

    /*public LiveData<List<Expenditure>> getAllCategory(String isSalary, String isAd, String isPurchase, String isOutfit){
        return expenditureDao.getAllCategory(isPurchase,isSalary,isOutfit,isAd);
    }*/

    public LiveData<List<Expenditure>> filterAllCategory(String travelling, String shoping, int category, String word){
        return expenditureDao.filterAllCategory(travelling,shoping,category,word);
    }
}
