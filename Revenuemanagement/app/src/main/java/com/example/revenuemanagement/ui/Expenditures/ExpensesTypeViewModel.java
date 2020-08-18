package com.example.revenuemanagement.ui.Expenditures;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.revenuemanagement.entity.ExpenditureType;
import com.example.revenuemanagement.repository.ExpenditureTypeResponsive;

import java.util.List;

public class ExpensesTypeViewModel extends AndroidViewModel {
    private ExpenditureTypeResponsive responsive;
    private LiveData<List<ExpenditureType>> getAll;
    public ExpensesTypeViewModel(@NonNull Application application) {
        super(application);
        responsive = new ExpenditureTypeResponsive(application);
        getAll = responsive.getGetAll();
    }

    public LiveData<List<ExpenditureType>> getGetAll() {
        return getAll;
    }

    public void insert(ExpenditureType expenditureType){
        responsive.insert(expenditureType);
    }

    public void update(ExpenditureType expenditureType){
        responsive.update(expenditureType);
    }

    public void delete(ExpenditureType expenditureType){
        responsive.delete(expenditureType);
    }

}
