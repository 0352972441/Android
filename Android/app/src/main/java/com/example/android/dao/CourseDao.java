package com.example.android.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android.entity.CourseEntity;

import java.util.List;

@Dao
public interface CourseDao {
    @Query("Select * from CourseEntity")
    List<CourseEntity> getAll();

    @Insert
    void insert(CourseEntity course);
    @Delete
    void delete(CourseEntity course);
    @Update
    void update(CourseEntity courseEntity);
}
