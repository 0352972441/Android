package com.example.lab7.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.lab7.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LessonThreeActivity extends AppCompatActivity {
    BottomNavigationView mBottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_three);
        mBottomNavigation = (BottomNavigationView)findViewById(R.id.bottomNavigation);
        mBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                String message = "";
                switch (menuItem.getItemId()){
                    case R.id.music :
                        message = "Music";
                        break;
                    case R.id.favorite :
                        message = "Favorite";
                        break;
                    case R.id.time :
                        message = "Time";
                        break;
                    default:
                        break;
                }
                Toast.makeText(LessonThreeActivity.this, message, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
