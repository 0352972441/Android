package com.example.assignmentandroid.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.assignmentandroid.entity.CourseEntity;

import java.util.List;

@Dao
public interface CourseDao {
    @Query("Select * from CourseEntity")
    public List<CourseEntity> getAll();

    @Insert
    void insert(CourseEntity course);
    @Delete
    void delete(CourseEntity course);
}
