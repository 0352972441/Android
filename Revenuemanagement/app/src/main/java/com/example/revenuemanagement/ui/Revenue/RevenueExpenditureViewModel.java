package com.example.revenuemanagement.ui.Revenue;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.revenuemanagement.entity.Revenue;
import com.example.revenuemanagement.repository.RevenueReponsitory;
import com.example.revenuemanagement.repository.RevenueTypeRepository;

import java.util.List;

public class RevenueExpenditureViewModel extends AndroidViewModel{
    RevenueReponsitory reponsitory;
    LiveData<List<Revenue>> getAll;
    public RevenueExpenditureViewModel(@NonNull Application application) {
        super(application);
        reponsitory = new RevenueReponsitory(application);
        getAll = reponsitory.getGetAll();
    }

    public LiveData<List<Revenue>> getGetAll() {
        return getAll;
    }

    public void insert(Revenue revenue){
        reponsitory.insert(revenue);
    }
    public void update(Revenue revenue){
        reponsitory.update(revenue);
    }
    public void delete(Revenue revenue){
        reponsitory.delete(revenue);
    }
}
