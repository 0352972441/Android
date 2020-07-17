package com.example.salebeer;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnclickAction(View view){
        BeerExpert expert = new BeerExpert();
        // get a reference to the textView
        TextView brand = (TextView) findViewById(R.id.brand);
        // get a reference to the Spinner
        Spinner beerType = (Spinner) findViewById(R.id.color);
        //get a Selectdted item in spinner and convert to Strng (Because it reuturn abject)
        String color = String.valueOf(beerType.getSelectedItem());
        // build a string using the value in list
        ArrayList<String> getBrand = expert.getBrand(color);
        StringBuilder brandFormat = new StringBuilder();
        for(String i: getBrand){
            brandFormat.append(i).append("\n");
        }
        brand.setText(brandFormat);
    }

}
