package com.example.note.Data;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

public class Transaction implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.8f;
    private static final float MIN_ALPHA = 0.7f;
    @Override
    public void transformPage(@NonNull View page, float position) {
        int heightPage = page.getHeight();
        int widthPage = page.getWidth();
        if(position < -1){
            page.setAlpha(0f);
        }else if(position <= 1){
            float scaleFactor = Math.max(MIN_SCALE, 1- Math.abs(position));
            float verticalMargin = heightPage * (1-scaleFactor)/2;
            float horizonMargin = widthPage * (1-scaleFactor)/2;
            if(position <0){
                page.setTranslationX((horizonMargin - verticalMargin)/2);
            }else{
                page.setTranslationX((-horizonMargin - verticalMargin)/2);
            }
            page.setScaleX(scaleFactor);
            page.setScaleX(scaleFactor);
            page.setAlpha(MIN_ALPHA+(scaleFactor - MIN_ALPHA)/(1-MIN_SCALE)*(1-MIN_ALPHA));
        }else {
            page.setAlpha(0f);
        }
    }
}
