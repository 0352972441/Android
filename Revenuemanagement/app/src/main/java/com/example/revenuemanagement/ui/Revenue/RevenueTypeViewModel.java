package com.example.revenuemanagement.ui.Revenue;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.revenuemanagement.entity.RevenueType;
import com.example.revenuemanagement.repository.RevenueTypeRepository;

import java.util.List;

public class RevenueTypeViewModel extends AndroidViewModel{
    LiveData<List<RevenueType>> getAll;
    RevenueTypeRepository repository;

    public RevenueTypeViewModel(Application application){
        super(application);
        repository = new RevenueTypeRepository(application);
        getAll = repository.getRevenueType();
    }

    public LiveData<List<RevenueType>> getGetAll() {
        return getAll;
    }

    public void insert(RevenueType revenueType){
        repository.insert(revenueType);
    }
    public void update(RevenueType revenueType){
        System.out.println("This is update Repontory View model");
        repository.update(revenueType);
    }
    public void delete(RevenueType revenueType){
        repository.delete(revenueType);
    }

}
