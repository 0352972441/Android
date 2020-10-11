package com.example.cocoshop.Screen.profileActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.cocoshop.R;
import com.example.cocoshop.dao.audiodao.MyFavoriteVocabularyDao;

public class MyFavoriteActivity extends AppCompatActivity {
    private RecyclerView my_favorite_recycler_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorite);
        my_favorite_recycler_view = (RecyclerView)findViewById(R.id.vocabulary_recycler_view);
        new MyFavoriteVocabularyDao(my_favorite_recycler_view,this).execute();
    }
}
