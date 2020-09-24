package com.example.revenuemanagement.ui.Expenditures;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Update;

import com.example.revenuemanagement.entity.Expenditure;
import com.example.revenuemanagement.repository.ExpenditureResponsive;

import java.util.List;

public class ExpenditureViewModel extends AndroidViewModel {
    private ExpenditureResponsive responsive;
    private LiveData<List<Expenditure>> getAll;
    public ExpenditureViewModel(@NonNull Application application) {
        super(application);
        responsive = new ExpenditureResponsive(application);
        // New
        this.getAll = responsive.getGetAll();
    }

    public LiveData<List<Expenditure>> getGetAll() {
        return getAll;
    }

    public void insert(Expenditure expenditure){
        responsive.insert(expenditure);
    }
    public void update(Expenditure expenditure){
        responsive.update(expenditure);
    }
    public void delete(Expenditure expenditure){
        responsive.delete(expenditure);
    }

    public LiveData<List<Expenditure>> getPuchase(String string){
        return responsive.getAllPurchases(string);
    }



    /*public LiveData<List<Expenditure>> getAllCategory(String isSalary, String isAd, String isPurchase, String isOutfit){
        return responsive.getAllCategory(isPurchase,isSalary,isOutfit,isAd);
    }*/


    public LiveData<List<Expenditure>> filterAllCategory(String travelling, String shoping, int category, String word){
        return responsive.filterAllCategory(travelling,shoping,category,word);
    }
}
