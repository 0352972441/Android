package com.example.revenuemanagement.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.revenuemanagement.entity.ExpenditureType;

import java.util.List;

@Dao
public interface ExpenditureTypeDao {
    @Query("SELECT * FROM expendituretype")
    LiveData<List<ExpenditureType>> getAll();


    ///
    @Insert
    void insert(ExpenditureType expenditureType);

    @Update
    void update(ExpenditureType expenditureType);

    @Delete
    void delete(ExpenditureType expenditureType);

}
