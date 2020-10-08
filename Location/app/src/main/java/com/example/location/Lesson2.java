package com.example.location;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Lesson2 extends AppCompatActivity {
    private TextView currentStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson2);
        currentStatus = (TextView)findViewById(R.id.currentStatus);
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

//For 3G check
        boolean is3g = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .isConnectedOrConnecting();
//For WiFi Check
        boolean isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .isConnectedOrConnecting();

       /* System.out.println(is3g + " net " + isWifi);*/

        if (!is3g && !isWifi)
        {
            Toast.makeText(getApplicationContext(),"Please make sure your Network Connection is ON ",Toast.LENGTH_LONG).show();
        }
        else
        {
            if(is3g){
                currentStatus.setText("Current Status 3G:"+ String.valueOf(is3g));
            }else{
                currentStatus.setText("Current Status Wifi:"+ String.valueOf(isWifi));
            }
        }
    }
}
