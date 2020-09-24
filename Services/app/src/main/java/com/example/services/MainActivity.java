package com.example.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.services.Services.MyService;
import com.example.services.Services.serviceFind;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createService(View view) {
        Intent intent = new Intent(this, MyService.class);
        Bundle bundle = new Bundle();
        bundle.putString("ID","Pd04077");
        bundle.putString("NAME","Toán");
        bundle.putString("CLASS","MOBILE");
        intent.putExtra("student",bundle);
        startService(intent);
    }

    public void stopservices(View view) {
        Intent intent = new Intent(this,MyService.class);
        stopService(intent);
    }

    public void onFind(View view) {
        int count = 0;
        EditText content = (EditText)findViewById(R.id.content);
        String  data = content.getText().toString();
        for(int i=0; i<data.length(); i++){
            if(String.valueOf(data.charAt(i)).equals("A")){
                count++;
            }
        }
        Bundle bundle = new Bundle();
        bundle.putInt("Count",count);
        Intent intent = new Intent(this, serviceFind.class);
        intent.putExtra("data",bundle);
        startService(intent);
    }
}
