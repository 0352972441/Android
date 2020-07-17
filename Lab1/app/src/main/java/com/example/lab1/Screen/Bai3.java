package com.example.lab1.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lab1.R;

public class Bai3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);
    }

    public void switchActivity(View view) {
        Intent intent = new Intent(this, Bai4.class);
        startActivity(intent);
    }
}
