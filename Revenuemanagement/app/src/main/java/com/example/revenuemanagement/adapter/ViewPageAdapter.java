package com.example.revenuemanagement.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class ViewPageAdapter extends FragmentStateAdapter {

    private ArrayList<Fragment> listFragment;

    public ViewPageAdapter(FragmentActivity fragmentActivity){
        super(fragmentActivity);
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

    public void AddFragment(Fragment fragment){
         listFragment.add(fragment);
    }
}
