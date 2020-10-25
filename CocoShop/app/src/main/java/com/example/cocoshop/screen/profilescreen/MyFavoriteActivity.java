package com.example.cocoshop.screen.profilescreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cocoshop.R;
import com.example.cocoshop.dao.topicdao.MyFavoriteVocabularyDao;
import com.example.cocoshop.screen.ReviewActivity;

public class MyFavoriteActivity extends AppCompatActivity {
    private RecyclerView my_favorite_recycler_view;
    private Button mBtnReview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorite);
        my_favorite_recycler_view = (RecyclerView)findViewById(R.id.vocabulary_recycler_view);
        mBtnReview = findViewById(R.id.btn_review);
        new MyFavoriteVocabularyDao(my_favorite_recycler_view,this).execute();
        onClickReview();
    }

    private void onClickReview(){
        mBtnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyFavoriteActivity.this, ReviewActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
