package com.example.designgraphics.PagerDisigner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class Pager extends FragmentPagerAdapter {
    public Pager(@NonNull FragmentManager fm) {
        super(fm);
    }
    private ArrayList<Fragment> fragment = new ArrayList<>();
    private ArrayList<String> title = new ArrayList<>();

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragment.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }

    @Override
    public int getCount() {
        return fragment.size();
    }

    public void addPager(Fragment fragment, String title){
        this.fragment.add(fragment);
        this.title.add(title);
    }
}
