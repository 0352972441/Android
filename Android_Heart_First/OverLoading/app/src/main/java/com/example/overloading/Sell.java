package com.example.overloading;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import  androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class Sell extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        Toolbar toolbar = (Toolbar)findViewById(R.id.bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void onClickComfirm(View view){
        Intent intent = null;
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String item = spinner.getSelectedItem().toString();
        if(item.equals("Store")){
            intent = new Intent(this,Store.class);
        }else if(item.equals("Address")){
            intent = new Intent(this,Address.class);
        }else if(item.equals("Menu")){
            intent = new Intent(this, Menu.class);
        }
        startActivity(intent);
    }
}
