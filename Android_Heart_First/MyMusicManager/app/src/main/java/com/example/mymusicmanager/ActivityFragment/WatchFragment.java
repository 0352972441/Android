package com.example.mymusicmanager.ActivityFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymusicmanager.Data.Buttons;
import com.example.mymusicmanager.R;

import java.util.Locale;
import java.util.zip.Inflater;


public class WatchFragment extends Fragment implements View.OnClickListener{

    private int seconds=0;
    private boolean state = false;
    private boolean running = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            state = savedInstanceState.getBoolean("state");
            running = savedInstanceState.getBoolean("running");
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_watch, container, false);
        Button start = (Button)layout.findViewById(R.id.start);
        start.setOnClickListener(this);
        Button stop = (Button)layout.findViewById(R.id.stop);
        stop.setOnClickListener(this);
        Button reset = (Button)layout.findViewById(R.id.reset);
        reset.setOnClickListener(this);
        runTimer(layout);
        return layout;
    }

    public void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putBoolean("state",state);
        saveInstanceState.putInt("seconds",seconds);
        saveInstanceState.putBoolean("running",running);
    }


    public void runTimer(View view){
        final Handler handler = new Handler();
        final  TextView textView = (TextView) view.findViewById(R.id.time);
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hour = seconds/3600;
                int minutes = (seconds%3600)/60;
                int  mili = seconds%60;
                String time = String.format(Locale.getDefault(),"%d:%02d:%02d",hour,minutes,mili);
                textView.setText(time);
                if(state){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });

    }

/*    public void onStop(){
        super.onStop();
        running = state;
        state = false;
    }

    public void onStart(){
        super.onStart();
        if(running){
            state = true;
        }
    }*/

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.start:
                state = true;break;
            case R.id.stop:
                state = false;break;
            case R.id.reset:
                state = false; seconds = 0;break;
            default:break;
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
