package com.example.lab7.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lab7.R;
import com.google.android.material.snackbar.Snackbar;

public class LessonOneActivity extends AppCompatActivity {
    Button mbg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_one);
        mbg = (Button)findViewById(R.id.bg_show);
        mbg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "I'm is Snackbar", Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(LessonOneActivity.this, "Undo", Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
    }
}
