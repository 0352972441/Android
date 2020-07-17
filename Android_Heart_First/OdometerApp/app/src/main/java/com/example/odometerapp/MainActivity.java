package com.example.odometerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.widget.TextView;

import com.example.odometerapp.Services.OdometerServers;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements ServiceConnection {
    private OdometerServers odometer;
    private final int  PERMISSION_REQUEST_CODE = 4343;
    private boolean bound = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView)findViewById(R.id.id);
        display();
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        OdometerServers.OdometerBinder binder = (OdometerServers.OdometerBinder) service;
        odometer = binder.getDometer();
        bound = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        bound = false;
    }

    public void onStart(){
        super.onStart();
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},PERMISSION_REQUEST_CODE);
        }else {
            Intent intent = new Intent(this, OdometerServers.class);
            bindService(intent, this, Context.BIND_AUTO_CREATE);
        }
    }

    public void onStop(){
        super.onStop();
        if(true){
            unbindService(this);
            bound = false;
        }
    }

    public void display(){
        final TextView textView = (TextView)findViewById(R.id.id);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                double distance = 0;
                if(bound && odometer != null){
                    distance = odometer.getDistance();
                }
                String time = String.format(Locale.getDefault(),"%1$,.2f milis",distance);
                textView.setText(time);
                handler.postDelayed(this,1000);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQUEST_CODE:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(this, OdometerServers.class);
                    bindService(intent,this, Context.BIND_AUTO_CREATE);
                }else{
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                    builder.setSmallIcon(android.R.drawable.sym_def_app_icon);
                    builder.setContentTitle("Location enable");
                    builder.setContentText("Please enable location GPS");
                    builder.setAutoCancel(true);
                    builder.setPriority(NotificationCompat.PRIORITY_HIGH);
                    builder.setVibrate(new long[]{1000,1000});
                    Intent intent = new Intent(this, MainActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    builder.setContentIntent(pendingIntent);
                    manager.notify(1212,builder.build());
                }
            }
        }
    }
}
