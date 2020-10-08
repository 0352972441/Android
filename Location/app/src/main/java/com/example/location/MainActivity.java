package com.example.location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void switchActivity(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.bntLs1 :
                intent = new Intent(MainActivity.this,Lesson1.class);
                startActivity(intent);
                break;
            case R.id.bntLs2:
                intent = new Intent(MainActivity.this,Lesson2.class);
                startActivity(intent);
                break;
        }
    }
}
