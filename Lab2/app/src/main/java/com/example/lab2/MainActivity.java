package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lab2.Screen.Bai1;
import com.example.lab2.Screen.Bai2;
import com.example.lab2.Screen.Bai3;
import com.example.lab2.Screen.Bai4;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void switchActivity(View view){
        Intent intent = null;
        switch (view.getId()){
            case R.id.btOne:
                intent = new Intent(this, Bai1.class);
                break;
            case R.id.btTwo:
                intent = new Intent(this, Bai2.class);
                break;
            case R.id.btThree:
                intent = new Intent(this, Bai3.class);
                break;
            case R.id.btFour:
                intent = new Intent(this, Bai4.class);
                break;
            default:
                return;
        }
        startActivity(intent);
    }
}
