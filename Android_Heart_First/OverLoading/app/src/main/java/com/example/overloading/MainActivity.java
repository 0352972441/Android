package com.example.overloading;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ShareActionProvider;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.add:
                Intent intent = new Intent(this,Sell.class);
                startActivity(intent);
                return true;
            case R.id.time:
                Intent clock = new Intent(this,Watch.class);
                startActivity(clock);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void onClickSend(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        EditText editText = (EditText)findViewById(R.id.message);
        String message = String.valueOf(editText.getText());
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,message);
        Intent chooose = intent.createChooser(intent,"Choose app share");
        startActivity(chooose);
    }
}
