package com.example.note;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.note.Activity.Note;
import com.example.note.ActivityFragment.MonthFragment;
import com.example.note.ActivityFragment.TodayFragment;
import com.example.note.ActivityFragment.WeekFragment;
import com.example.note.Data.TabLayouts;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.note.Data.Transaction;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addPages();
    }

    public void addPages(){
        TabLayouts tabLayouts = new TabLayouts(getSupportFragmentManager());
        tabLayouts.setFragment(new TodayFragment(),getResources().getString(R.string.today));
        tabLayouts.setFragment(new WeekFragment(),getResources().getString(R.string.week));
        tabLayouts.setFragment(new MonthFragment(),getResources().getString(R.string.hot));
        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpages);
        viewPager.setAdapter(tabLayouts);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        Transaction transaction = new Transaction();
        viewPager.setPageTransformer(true,transaction);
        tabLayout.getTabAt(0).setIcon(R.drawable.home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_action_name);
        tabLayout.getTabAt(2).setIcon(R.drawable.hot);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return  super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.note){
            Intent intent = new Intent(this,Note.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
