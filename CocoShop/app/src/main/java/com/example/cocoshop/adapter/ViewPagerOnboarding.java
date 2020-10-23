package com.example.cocoshop.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerOnboarding extends FragmentStateAdapter {
    public List<Fragment> listFragment;
    public ViewPagerOnboarding(FragmentActivity manager){
        super(manager);
        listFragment = new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return listFragment.get(position);
    }


    @Override
    public int getItemCount() {
        return listFragment.size();
    }

    public void addFragment(Fragment fragment){
        listFragment.add(fragment);
    }
}
