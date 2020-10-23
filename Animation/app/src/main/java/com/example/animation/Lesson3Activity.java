package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.animation.animation.Animations;

import java.util.Timer;
import java.util.TimerTask;

public class Lesson3Activity extends AppCompatActivity {
    private ImageView imgSecond,imgMinute,imgHour;
    private Animations animations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson3);
        animations = new Animations(this);
        imgSecond = findViewById(R.id.img_seconds);
        imgMinute = findViewById(R.id.img_minutes);
        imgHour = findViewById(R.id.img_hours);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                Animation animation = animations.rotate(60000);
                imgSecond.startAnimation(animation);
                handler.postDelayed(this,59999);
            }
        });
        final Handler handler1 = new Handler();
        handler1.post(new Runnable() {
            @Override
            public void run() {
                Animation secondAnimation = animations.rotate(60*60*1000);
                imgMinute.startAnimation(secondAnimation);
                handler1.postDelayed(this,3600000);
            }
        });

        final Handler handler2 = new Handler();
        handler2.post(new Runnable() {
            @Override
            public void run() {
                imgHour.startAnimation(animations.rotate(24*3600000));
                handler2.postDelayed(this,24*3600000);
            }
        });
    }
}
