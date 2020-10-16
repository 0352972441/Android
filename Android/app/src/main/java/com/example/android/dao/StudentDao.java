package com.example.android.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android.entity.StudentEntity;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface StudentDao {
    @Query("SELECT * FROM StudentEntity")
    List<StudentEntity> getAll();

    @Delete
    void delete (StudentEntity studentEntity);
    @Update
    void update (StudentEntity studentEntity);
    @Insert
    void insert(StudentEntity studentEntity);
}
