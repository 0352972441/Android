package com.example.cocoshop.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.cocoshop.MainActivity;
import com.example.cocoshop.R;
import com.example.cocoshop.screen.authscreen.LoginScreen;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSharePerferces();
    }

    private void intentLogin(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void getSharePerferces(){
        SharedPreferences sharedPreferences = this.getSharedPreferences("buzzbee",MODE_PRIVATE);
        if(sharedPreferences != null){
            boolean run = sharedPreferences.getBoolean("run",false);
            Toast.makeText(this, String.valueOf(run), Toast.LENGTH_SHORT).show();
            if(run){
                Intent intent = new Intent(this, LoginScreen.class);
                startActivity(intent);
                finish();
            }else{
                intentLogin();
                saveRunAppFirst();
            }
        }
    }

    public void saveRunAppFirst() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("buzzbee", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("run", true);
        editor.apply();
    }
}