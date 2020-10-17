package com.example.android.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.android.entity.MyCourseEntity;

import java.util.List;

@Dao
public interface MyCourseDao {
    @Query("Select * from MyCourseEntity")
    List<MyCourseEntity> getAll();

    @Insert
    void insert(MyCourseEntity course);
    @Delete
    void delete(MyCourseEntity course);
}