package com.example.watch;

import android.app.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends Activity {
    private int second = 0;
    private boolean running;
    private boolean wasRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            second = savedInstanceState.getInt("seconds");
            running =savedInstanceState.getBoolean("run");
            wasRunning = savedInstanceState.getBoolean("was");
        }
        watchTime();
    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds",second);
        savedInstanceState.putBoolean("run",running);
        savedInstanceState.putBoolean("was",wasRunning);
    }



    public void onPause(){
        super.onPause();
        wasRunning = running;
        running = false;
    }

    public void onStart(){
        super.onStart();
        if(wasRunning){
            running = true;
        }
    }

    public void onResume(){
        super.onResume();
        if(wasRunning) {
            running = true;
        }
    }


    public void onClickStart(View view){
        running = true;
    }

    public void onClickStop(View view){
        running = false;
    }

    public void onClickReset(View view){
        second = 0;
        running = false;
    }

    public void watchTime(){
        final TextView textView = (TextView) findViewById(R.id.time_out);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = second/3600;
                int minute = (second%3600)/60;
                int sec = second%60;
                String time = String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minute,sec);
                textView.setText(time);
                if(running){
                    second++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }

}
