package com.example.overloading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Detail extends AppCompatActivity {
    public static final String EXTRA = "key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getIntentDetail();
    }

    public void getIntentDetail(){
        Intent intent = getIntent();
        int index = intent.getExtras().getInt(EXTRA);
        CurrentAddress currentAddress = CurrentAddress.currentAddresses[index];
        TextView title = (TextView) findViewById(R.id.title);
        TextView description = (TextView)findViewById(R.id.description);
        title.setText(currentAddress.getAddress());
        description.setText(currentAddress.getDescription());
    }

}
