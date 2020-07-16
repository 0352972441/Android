package com.example.myapplication.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.ViewModel.MessageViewModel;

public class Message extends AppCompatActivity {
    public static final String EXTRA ="key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        getMessage();
    }
    public void getMessage(){
        Intent intent = getIntent();
        int index = intent.getExtras().getInt(EXTRA);
        MessageViewModel ms = MessageViewModel.one[index];
        TextView textView = (TextView)findViewById(R.id.message);
        TextView textView1 = (TextView)findViewById(R.id.message1);
        textView.setText(ms.getTitle()+ms.getMessage1()+ms.getMessage2());
        textView1.setText(ms.getMessage());
    }
}
