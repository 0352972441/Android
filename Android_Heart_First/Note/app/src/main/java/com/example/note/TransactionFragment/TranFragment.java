package com.example.note.TransactionFragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.note.ActivityFragment.StoryFragment;
import com.example.note.R;

public class TranFragment {
    public static void add(FragmentManager manager){
        StoryFragment storyFragment = new StoryFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container,storyFragment);
        /*transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);*/
        transaction.commit();
    }
}
