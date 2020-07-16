package com.example.audio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    public void play(View view){
        mediaPlayer.start();
        final ImageView imageView = (ImageView)findViewById(R.id.imageview);
        imageView.animate().rotationY(360).setDuration(mediaPlayer.getDuration());
    }

    public void pause(View view){
        mediaPlayer.stop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // lay trinh quan ly audio
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        mediaPlayer = MediaPlayer.create(this,R.raw.audio);
        // Lay am luong toi da cua thiet bi
        int maxVolume = audioManager.getStreamMaxVolume(audioManager.STREAM_MUSIC);
        // lay am luong hien tai
        final int currentVolume = audioManager.getStreamVolume(audioManager.STREAM_MUSIC);
        final SeekBar seekBar = (SeekBar)findViewById(R.id.seekbar);
        // set gia tri am luong toi da khi keo seekbar
        seekBar.setMax(maxVolume);
        // set qua trinh
        seekBar.setProgress(currentVolume);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Toast.makeText(MainActivity.this, Integer.toString(progress), Toast.LENGTH_SHORT).show();
                // thiet lap am luong cho manager audio -> device
                audioManager.setStreamVolume(audioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Seek bar touch", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Not touch", Toast.LENGTH_SHORT).show();
            }
        });

        final SeekBar curSeekBar = (SeekBar)findViewById(R.id.curseekbar);
        curSeekBar.setMax(mediaPlayer.getDuration());
        curSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    //Toast.makeText(MainActivity.this, "ToanKPQ Developer Kill Programe ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Music end", Toast.LENGTH_SHORT).show();
            }
        });
        final Handler handler =  new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                curSeekBar.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this,1000);
            }
        });

        /*new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

            }
        },0,300);*/
    }
}
