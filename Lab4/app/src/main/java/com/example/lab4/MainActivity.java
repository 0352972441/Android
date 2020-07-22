package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lab4.Screen.Lesson2Activity;
import com.example.lab4.Screen.Lesson3Activity;
import com.example.lab4.Screen.lesson1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void switchActivity(View view){
        Intent intent = null;
        switch (view.getId()){
            case R.id.btOne :
                intent = new Intent(this, lesson1.class);
                break;
            case R.id.btTwo :
                intent = new Intent(this, Lesson2Activity.class);
                break;
            case R.id.btThree :
                intent = new Intent(this, Lesson3Activity.class);
                break;
            case R.id.btFour :
                intent = new Intent(this, null);
                break;
            default:
                return;
        }
        startActivity(intent);
    }
}
