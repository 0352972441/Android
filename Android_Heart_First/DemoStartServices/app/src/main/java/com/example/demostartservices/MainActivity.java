package com.example.demostartservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.demostartservices.Services.DelayMessageServices;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button onClick = (Button) findViewById(R.id.button);
        onClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DelayMessageServices.class);
                intent.putExtra(DelayMessageServices.EXTRA_MESSAGE,
                        getResources().getString(R.string.response));
                startService(intent);
            }
        });
    }




//    public void onClick(View view) {
//
//    }
}
