package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void switchActivity(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.btn1:
                intent = new Intent(this,Lesson1Activity.class);
                startActivity(intent);
                break;
            case R.id.btn2:
                intent = new Intent(this,Lesson2Activity.class);
                startActivity(intent);
                break;
            case R.id.btn3:
                intent = new Intent(this,Lesson3Activity.class);
                startActivity(intent);
                break;
        }
    }
}
