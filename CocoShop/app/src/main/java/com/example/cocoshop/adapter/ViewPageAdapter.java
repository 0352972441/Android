package com.example.cocoshop.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;
import java.util.Map;

public class ViewPageAdapter extends FragmentStateAdapter {
    private List<Map<String,Object>> listFragment;

    public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity, List<Map<String,Object>> listFragment) {
        super(fragmentActivity);
        this.listFragment = listFragment;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return (Fragment) listFragment.get(position).get("fragment");
    }

    @Override
    public int getItemCount() {
        return listFragment.size();
    }


}
