package com.example.lab8.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lab8.MainActivity;
import com.example.lab8.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void switchActivity(View view) {
        Intent activityIntent = null;
        if(view.getId() == R.id.btnL1){
            activityIntent = new Intent(this, MainActivity.class);
            startActivity(activityIntent);
        }else if(view.getId() == R.id.btnL2){
            activityIntent = new Intent(this,Lesson2Activity.class);
            startActivity(activityIntent);
        }
    }
}
