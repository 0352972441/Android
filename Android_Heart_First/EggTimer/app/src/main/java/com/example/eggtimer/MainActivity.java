package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView textView;
    public void go( View view){
        CountDownTimer timer = new CountDownTimer(seekBar.getProgress()*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer((int)millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "End", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

    public void updateTimer(int second){
        int minute = second / 60;
        int seconds = second-(minute *60);
        String timer = String.format(Locale.getDefault(),"%02d : %02d",minute,seconds);
        textView = (TextView)findViewById(R.id.timer);
        textView.setText(timer);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = (SeekBar)findViewById(R.id.seekbar);
        seekBar.setMax(600);
        seekBar.setProgress(300);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
