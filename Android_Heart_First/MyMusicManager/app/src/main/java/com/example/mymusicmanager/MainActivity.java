package com.example.mymusicmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.mymusicmanager.Activity.ActivityDetal;
import com.example.mymusicmanager.Activity.ActivityDetal;
import com.example.mymusicmanager.ActivityFragment.FragmentDetail;
import com.example.mymusicmanager.ListenerFragmenr.Listenner;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Listenner {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void clickitem(long id) {
        View fragmentcontainer = (View) findViewById(R.id.fragment_contain);
        if(fragmentcontainer != null){
            FragmentDetail fragmentDetail = new FragmentDetail();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_contain,fragmentDetail);
            transaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.addToBackStack(null);
            transaction.commit();
        }else {
            Intent intent = new Intent(this, ActivityDetal.class);
            intent.putExtra(ActivityDetal.EXTRA, id);
            startActivity(intent);
        }
    }
}
