package com.example.cocoshop.dao.audiodao;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.cocoshop.Adapter.topicsadapter.Multiple_Choice_Adapter;
import com.example.cocoshop.R;
import com.example.cocoshop.listener.OnClickListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class Multiple_Choice_Dao extends AsyncTask<Void,Void,Void> {
    private CardView cardAnswer;
    private TextView score;
    private MediaPlayer answerSound;
    private FrameLayout bottom_sheet;
    private int color;
    private static int point = 0;
    int sound;
    public Multiple_Choice_Dao(CardView cardAnser, TextView score, FrameLayout bottom_sheet, int color,int sound) {
        this.cardAnswer = cardAnser;
        this.score = score;
        this.bottom_sheet = bottom_sheet;
        this.color = color;
        this.sound = sound;
    }

    @Override
    protected Void doInBackground(Void... maps) {
        onProgressUpdate(null);
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        answerSound = MediaPlayer.create(bottom_sheet.getContext(),sound);
        answerSound.start();
        bottom_sheet.setVisibility(View.VISIBLE);
        cardAnswer.setBackgroundColor(color);
        score.setText(String.valueOf(point += 100)+ " Point");
    }
}
