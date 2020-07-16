package com.example.gridlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    public void playphrases(View view){
        Button button = (Button)view;
        createAudio(button.getTag().toString());
        mediaPlayer.start();
    }

    public void createAudio(String nameOfFile){
        mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier(nameOfFile,"raw",getPackageName()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
