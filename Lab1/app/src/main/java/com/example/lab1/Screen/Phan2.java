package com.example.lab1.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lab1.R;

public class Phan2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phan2);
    }

    public void SwitchActivity(View view) {
        Intent intent = new Intent(this, Bai3.class);
        startActivity(intent);
    }
}
