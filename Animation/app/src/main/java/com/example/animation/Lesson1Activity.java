package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.animation.animation.Animations;

public class Lesson1Activity extends AppCompatActivity {
    private Button btnZoom,btnMoving,btnRotate;
    private ImageView image;
    private Animations animations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson1);
        animations = new Animations(this);
        image = findViewById(R.id.image);
        btnMoving = findViewById(R.id.moving);
        btnRotate = findViewById(R.id.rotate);
        btnZoom = findViewById(R.id.zoom);
        btnZoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.startAnimation(animations.zoonIn(2000));
            }
        });
        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.startAnimation(animations.rotate(2000));
            }
        });
        btnMoving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.startAnimation(animations.move(2000));
            }
        });
    }
}
