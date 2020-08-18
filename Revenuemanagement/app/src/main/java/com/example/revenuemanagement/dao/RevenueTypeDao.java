package com.example.revenuemanagement.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.revenuemanagement.entity.RevenueType;

import java.util.List;
@Dao
public interface RevenueTypeDao {
    // Lây tất dữ liệu có trong bản Entity RevenueType
    @Query("SELECT * FROM RevenueType")
    LiveData<List<RevenueType>> getAll();
    // Chèn giữ liệu vào Bảng RevenueType(Loại thu)
    @Insert
    void insert (RevenueType revenueType);
    // Cập nhật dữ liệu cho bảng Revenue Type
    @Update
    void update(RevenueType revenueType);
    @Delete
    void delete(RevenueType revenueType);
}
