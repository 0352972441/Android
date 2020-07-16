package com.example.stopwatch;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends Activity {
    private  Boolean running;
    private int second =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeRuning();
    }

    public void start(View view){
        running = true;
    }

    public void stop(View view){
        running = false;
    }

    public void reset(View view){
        running = false;
        second = 0;
    }

    public void timeRuning(){
        final Handler handler = new Handler();
        final TextView textView = (TextView) findViewById(R.id.time);

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hour = second/60;
                int minute = (second%3600)/60;
                int sec = second%60;
                String time = String.format(Locale.getDefault(),"%d:%02d:%02d",hour,minute,sec);
                textView.setText(time);
                if(running){
                    second++;
                }
                handler.postDelayed(this, 1000);
            }
        });


    }

}
