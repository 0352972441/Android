package com.example.cocoshop.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.cocoshop.R;
import com.example.cocoshop.screen.profilescreen.MyFavoriteActivity;

public class FinishReviewScreen extends AppCompatActivity {
    private TextView mTxPlayerReview;
    private LinearLayout mReviewRate;
    private Button mBtnFinishReview;
    public static final String REVIEW = "review";
    public static final String NUMBEROFQUESTION = "question";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_review_screen);
        mTxPlayerReview = findViewById(R.id.tx_player_review);
        mReviewRate = findViewById(R.id.review_rate);
        mBtnFinishReview = findViewById(R.id.btn_complete);
        evaluate();
        mBtnFinishReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinishReviewScreen.this, MyFavoriteActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    private void evaluate(){
        int numberOfQuestionNotWrong = (int)getIntent().getExtras().getInt(NUMBEROFQUESTION);
        int totalOfQuestion = (int)getIntent().getExtras().getInt(REVIEW);
        if(numberOfQuestionNotWrong==totalOfQuestion){
            numberOfStarsAchieved(3);
            mTxPlayerReview.setText("Very good");
            mTxPlayerReview.setTextColor(R.color.yellow);

        }else if(numberOfQuestionNotWrong > (totalOfQuestion > 2 ? totalOfQuestion -2 : 0)){
            numberOfStarsAchieved(2);
            mTxPlayerReview.setText("Good");
            mTxPlayerReview.setTextColor(R.color.colorPrimary);
        }else if(numberOfQuestionNotWrong > totalOfQuestion -totalOfQuestion/2){
            numberOfStarsAchieved(1);
            mTxPlayerReview.setText("Bad");
            mTxPlayerReview.setTextColor(R.color.blue_sky_color);
        }else if(numberOfQuestionNotWrong == 0){
            numberOfStarsAchieved(0);
            mTxPlayerReview.setText("Very bad");
            mTxPlayerReview.setTextColor(R.color.grey);
        }
    }

    private void numberOfStarsAchieved(int numberofStars){
        ImageView[] star = new ImageView[3];
        for (int i=0; i< 3; i++){
            ImageView img_Star = new ImageView(this);
            img_Star.setImageResource(R.drawable.ic_star_border_none_unfinish_24dp);
            star[i] = img_Star;
        }
        for(int i=0; i< numberofStars; i++){
            ImageView achieved = star[i];
            achieved.setImageResource(R.drawable.ic_star_black_80dp);
            mReviewRate.addView(star[i]);
        }
    }
}
