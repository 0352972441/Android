package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.animation.animation.Animations;

public class Lesson2Activity extends AppCompatActivity {
    private Button btnNobita,btnSizuka, btnSuneo,btnAll;
    private ImageView imgAll;
    private Animations animations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson2);
        btnAll = findViewById(R.id.btn_doraemon);
        btnSizuka = findViewById(R.id.btn_sizuka);
        btnSuneo = findViewById(R.id.btn_suneo);
        btnNobita = findViewById(R.id.btn_nobita);
        imgAll = findViewById(R.id.image);
        animations = new Animations(this);
        btnNobita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage("Nobita");
            }
        });
        btnSuneo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage("Suneo");
            }
        });
        btnSizuka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage("Sizuka");
            }
        });
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage("All");
            }
        });
    }

    private void showImage(final String img){
        @SuppressLint("ObjectAnimatorBinding") ObjectAnimator anim = ObjectAnimator.ofFloat(imgAll,"translationX",0f,500f);
        anim.setDuration(2000);
        @SuppressLint("ObjectAnimatorBinding") ObjectAnimator anim2 = ObjectAnimator.ofFloat(imgAll,"alpha",1f,0f);
        anim.setDuration(2000);
        @SuppressLint("ObjectAnimatorBinding") ObjectAnimator anim3 = ObjectAnimator.ofFloat(imgAll,"translationX",-500f,0f);
        anim.setDuration(2000);
        @SuppressLint("ObjectAnimatorBinding") ObjectAnimator anim4 = ObjectAnimator.ofFloat(imgAll,"alpha",0f,1f);
        anim.setDuration(2000);
        /*ObjectAnimator anim = animations.animator("imgAll","translationX",0f,500f,2000);
        ObjectAnimator anim2 = animations.animator("imgAll","alpha",0f,1f,2000);
        ObjectAnimator anim3 = animations.animator("imgAll","translationX",-500f,0f,2000);
        ObjectAnimator anim4 = animations.animator("imgAll","alpha",1f,0f,2000);*/
        AnimatorSet animationSet = new AnimatorSet();
        animationSet.play(anim3).with(anim4).after(anim).after(anim2);
        animationSet.start();
        anim2.addListener(new Animator. AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }


            @Override
            public void onAnimationEnd(Animator animation) {
                if(img =="Nobita"){
                    imgAll.setImageResource(R.drawable.nobita);
                }else if(img == "Suneo"){
                    imgAll.setImageResource(R.drawable.suneo);
                }else if(img == "Sizuka"){
                    imgAll.setImageResource(R.drawable.sizuka);
                }else if(img =="All"){
                    imgAll.setImageResource(R.drawable.alldoraemon);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
