package com.example.services.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onStart(Intent intent, int startId) {

        Toast.makeText(this, "This is services", Toast.LENGTH_SHORT).show();
        Bundle bunder = intent.getBundleExtra("student");
        String id = bunder.getString("ID");
        String name = bunder.getString("NAME");
        String className = bunder.getString("CLASS");
        String content = "Thêm thông tin sinh viên thành công.\n Thông tin sinh viên là:\n "+id+"\n "+name+" \n"+className;
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Services onDestroy", Toast.LENGTH_LONG).show();
    }
}
