package com.example.revenuemanagement.dao;

import android.icu.text.Replaceable;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.revenuemanagement.entity.Expenditure;

import java.util.List;

@Dao
public interface ExpenditureDao {

    @Query("SELECT * FROM Expenditure")
    LiveData<List<Expenditure>> getAll();

    @Query("SELECT * FROM Expenditure where category = :isPurchase or category = :isSalary or category = :isOutfit or category =:isAd")
    LiveData<List<Expenditure>> getAllCategory(String isPurchase, String isSalary, String isOutfit, String isAd);

    @Query("SELECT * FROM Expenditure WHERE category = :isCategory ")
    LiveData<List<Expenditure>> getPurchase(String isCategory);

   /* @Query("UPDATE Expenditure set isPurchases = :isPurchase where category = :category")
    void updateIsPurchases(boolean isPurchase, String category);*/

    //===============
    @Insert
    void insert(Expenditure expenditure);

    @Update
    void update(Expenditure expenditure);


    @Delete
    void delete(Expenditure expenditure);

}
