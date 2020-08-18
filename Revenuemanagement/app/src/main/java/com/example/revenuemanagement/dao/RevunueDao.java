package com.example.revenuemanagement.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.revenuemanagement.entity.Revenue;

import java.util.List;

@Dao
public interface RevunueDao {
    @Query("SELECT * from Revenue")
    LiveData<List<Revenue>> getAll();

    @Insert
    void insert(Revenue revenue);

    @Update
    void update(Revenue revenue);

    @Delete
    void delete(Revenue revenue);

}
