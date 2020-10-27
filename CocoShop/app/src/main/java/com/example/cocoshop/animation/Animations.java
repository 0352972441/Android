package com.example.cocoshop.animation;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;

import com.example.cocoshop.R;

public class Animations{

    private Context context;

    public Animations(Context context) {
        this.context = context;
    }

    public Animation zoomOut(long duration){
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.button_animation);
        animation.setDuration(duration);
        return animation;
    }

    public Animation fadeIn(long duration){
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.logo_animation);
        animation.setDuration(duration);
        return animation;
    }

    public Animation zoonIn(long duration){
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.zoon_in);
        animation.setDuration(duration);
        return animation;
    }

    public Animation move(long duration){
        Animation  animation = AnimationUtils.loadAnimation(context,R.anim.move);
        animation.setDuration(duration);
        return animation;
    }

    public Animation rotate(long duration){
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.rotate);
        animation.setDuration(duration);
        animation.setRepeatCount(Animation.INFINITE);
        return animation;
    }

    public Animation moveDown(long duration){
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.move_down);
        animation.setDuration(duration);
        return animation;
    }

    public Animation moveUp(long duration){
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.move_up);
        animation.setDuration(duration);
        return animation;
    }

    public Animation vibrate(long duration){
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.vibrate);
        animation.setDuration(duration);
        return animation;
    }

    public Animation fadeInText(long duration){
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.fade_in);
        animation.setDuration(duration);
        return animation;
    }

    public Animation zoomOutText(long duration){
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.zoom_out);
        animation.setDuration(duration);
        return animation;
    }

    @NonNull
    public void stopAnimation(Animation animation){
        animation.cancel();
    }
}
