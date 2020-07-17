package com.example.lab1.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lab1.MainActivity;
import com.example.lab1.R;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void switchActivity(View view){
        Intent intent ;
        if(view.getId() == R.id.bt1){
            intent = new Intent(this, MainActivity.class);
        }else if(view.getId() == R.id.bt2){
             intent = new Intent(this, Phan2.class);
        }else if(view.getId() == R.id.bt3){
             intent = new Intent(this, Bai3.class);
        }else if(view.getId() == R.id.bt4){
             intent = new Intent(this, Bai4.class);
        }else{
            return;
        }
        startActivity(intent);
    }
}
