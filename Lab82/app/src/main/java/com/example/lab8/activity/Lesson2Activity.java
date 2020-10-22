package com.example.lab8.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.lab8.R;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Lesson2Activity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private ImageView imgPlay,imgStop,imgPause;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson2);
        mediaPlayer = MediaPlayer.create(this,R.raw.sound);
        imgPlay = findViewById(R.id.img_play);
        imgStop = findViewById(R.id.img_stop);
        imgPause = findViewById(R.id.img_pause);
        seekBar = findViewById(R.id.seek_bar);
        Toast.makeText(this, mediaPlayer.getDuration()+"", Toast.LENGTH_SHORT).show();
        seekBar.setMax(mediaPlayer.getDuration());
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this,1000);
            }
        });
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });
        imgPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });
        imgStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                seekBar.setProgress(0);
            }
        });
    }
}
