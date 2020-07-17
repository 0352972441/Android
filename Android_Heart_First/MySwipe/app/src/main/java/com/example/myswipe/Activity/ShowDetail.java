package com.example.myswipe.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myswipe.Data.Listenner;
import com.example.myswipe.Data.SnackBars;
import com.example.myswipe.R;
import com.google.android.material.snackbar.Snackbar;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

public class ShowDetail extends AppCompatActivity {
    public static final String EXTRA = "key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onClickAction(View view){
        CharSequence message = "Your update confirm";
        /*Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinator),message,Snackbar.LENGTH_LONG);
        Listenner listenner = new Listenner();
        snackbar.setAction("undo",listenner);
        snackbar.show();*/
        Snackbar snackbar = SnackBars.make(findViewById(R.id.coordinator),message,Snackbar.LENGTH_LONG);
        SnackBars.setAction(snackbar,"Undo",new Listenner());
        SnackBars.show(snackbar);
    }
}
