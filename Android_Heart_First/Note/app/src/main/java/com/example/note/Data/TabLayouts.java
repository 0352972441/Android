package com.example.note.Data;

import android.media.Image;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class TabLayouts extends FragmentPagerAdapter {
    private  final ArrayList<Fragment> fragments = new ArrayList<>();
    private  final ArrayList<String> titleFragment = new ArrayList<>();
    public TabLayouts(FragmentManager manager){
        super(manager);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Nullable
   /* @Override
    public CharSequence getPageTitle(int position) {
        return titleFragment.get(position);
    }*/

    @Override
    public int getCount() {
        return fragments.size();
    }
    public void setFragment(Fragment fragment, String title){
        fragments.add(fragment);
        titleFragment.add(title);
    }
}
