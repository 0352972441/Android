package com.example.lab6.Models;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class AdpaterViewPages extends FragmentPagerAdapter {
    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<String> title = new ArrayList<>();
    public AdpaterViewPages(FragmentManager manager){
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    public void createTab(Fragment fragment, String title){
        this.fragments.add(fragment);
        this.title.add(title);
    }
}