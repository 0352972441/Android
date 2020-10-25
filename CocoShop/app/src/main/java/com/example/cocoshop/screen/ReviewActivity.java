package com.example.cocoshop.screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.os.Bundle;

import com.example.cocoshop.R;
import com.example.cocoshop.dao.ReviewFavoriteVocabularyDao;

public class ReviewActivity extends AppCompatActivity {
    private ViewPager2 viewPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        viewPage = findViewById(R.id.review_view_page);
        new ReviewFavoriteVocabularyDao(viewPage,this).execute();
    }
}
