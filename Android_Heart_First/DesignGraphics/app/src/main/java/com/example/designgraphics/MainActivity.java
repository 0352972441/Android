package com.example.designgraphics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;


import com.example.designgraphics.Fragment.BookFragment;
import com.example.designgraphics.PagerDisigner.Pager;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createPager();
    }

    public void createPager(){
        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        TabLayout tableLayout = (TabLayout) findViewById(R.id.tablayout);
        Pager pager = new Pager(getSupportFragmentManager());
        pager.addPager(new BookFragment(),"BOOK");
        pager.addPager(new BookFragment(),"PROJECT");
        pager.addPager(new BookFragment(),"HOME");
        viewPager.setAdapter(pager);
        tableLayout.setupWithViewPager(viewPager);
    }
}
