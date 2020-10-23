package com.example.animation.animation;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.annotation.NonNull;

import com.example.animation.R;

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

    public ObjectAnimator animator(String target, String property, float start, float end,long duration){
        ObjectAnimator anim = ObjectAnimator.ofFloat(target,property,start,end);
        anim.setDuration(duration);
        return anim;
    }

    @NonNull
    public void stopAnimation(Animation animation){
        animation.cancel();
    }
}
