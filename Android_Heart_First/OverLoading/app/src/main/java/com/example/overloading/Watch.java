package com.example.overloading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.os.Handler;
import android.widget.TextView;

import java.util.Locale;

public class Watch extends AppCompatActivity {
    private int seconds=0;
    private boolean state;
    private boolean running;
    static final Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        if(savedInstanceState != null) {
            state = savedInstanceState.getBoolean("state");
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        Clock clock = new Clock();
        handler.post(clock);
    }

    class Clock implements Runnable{
        @Override
        public void run() {
            int hour = seconds/3600;
            int minutes = (seconds%3600)/60;
            int mili = seconds%60;
            String time = String.format(Locale.getDefault(),"%d:%02d:%02d",hour,minutes,mili);
            TextView textView = (TextView) findViewById(R.id.time);
            textView.setText(time);
            if(state){
                seconds++;
            }
            handler.postDelayed(this,1000);
        }
    }

    public void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putBoolean("state",state);
        saveInstanceState.putInt("seconds",seconds);
        saveInstanceState.putBoolean("running",running);
    }


    public void onClickStart(View view){
        state = true;
    }
    public void onClickReset(View view){
        state = false;
        seconds = 0;
    }
    public void onClickStop(View view){
        state = false;
    }

    public void onStop(){
        super.onStop();
        running = state;
        state = false;
    }

    public void onStart(){
        super.onStart();
        if(running){
            state = true;
        }
    }

    public void onPause(){
        super.onPause();
        running = state;
        state = false;
    }
    public void onResume(){
        super.onResume();
        if(running){
            state = true;
        }
    }

}
