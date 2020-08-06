package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lab6.Screen.LessonFourActivity;
import com.example.lab6.Screen.LessonOneActivity;
import com.example.lab6.Screen.LessonThreeActivity;
import com.example.lab6.Screen.LessonTwoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void switchActivity(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.btOne:
                intent = new Intent(this, LessonOneActivity.class);
                break;
            case R.id.btTwo:
                intent = new Intent(this, LessonTwoActivity.class);
                break;
            case R.id.btThree:
                intent = new Intent(this, LessonThreeActivity.class);
                break;
            /*case R.id.btFour:
                intent = new Intent(this, LessonFourActivity.class);
                break;*/
            default: return;
        }
        startActivity(intent);
    }
}
