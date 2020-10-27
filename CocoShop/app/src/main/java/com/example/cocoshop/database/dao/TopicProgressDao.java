package com.example.cocoshop.database.dao;

import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cocoshop.database.entity.TopicProgressEntity;

@Dao
public interface TopicProgressDao {
    @Query("SELECT * FROM TopicProgressEntity WHERE id == :id")
    TopicProgressEntity getProgress(int id);

    @Insert
    void insert(TopicProgressEntity topicProgressEntity);

    @Update
    void update(TopicProgressEntity topicProgressEntity);
}
