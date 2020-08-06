package com.example.lab6.Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.lab6.Fragment.LeftFragment;
import com.example.lab6.Fragment.RightFragment;
import com.example.lab6.Fragment.Tab3;
import com.example.lab6.Models.AdpaterViewPages;
import com.example.lab6.R;
import com.google.android.material.tabs.TabLayout;

public class LessonThreeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_three);
        setPage();
    }

    public void setPage(){
        AdpaterViewPages adapterPager = new AdpaterViewPages(getSupportFragmentManager());
        adapterPager.createTab(new LeftFragment(),"Tab1");
        adapterPager.createTab(new RightFragment(),"Tab2");
        adapterPager.createTab(new Tab3(),"Tab3");
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab);
        ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(adapterPager);
        tabLayout.setupWithViewPager(viewPager);
    }
}
