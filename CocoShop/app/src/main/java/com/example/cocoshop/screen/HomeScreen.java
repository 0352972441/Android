package com.example.cocoshop.screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.cocoshop.R;
import com.example.cocoshop.dao.AudioDao;
import com.example.cocoshop.fragment.TopicFragment;
import com.example.cocoshop.fragment.ChatFragment;
import com.example.cocoshop.fragment.AudioFragment;
import com.example.cocoshop.fragment.ProfileFragment;
import com.example.cocoshop.fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeScreen extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    public static String isCurrentFragment = "";
    private static Context context;
    //private ActionBar tabBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavigatorView);
        bottomNavigationView.setItemIconTintList(null);
        //tabBar = getSupportActionBar();
        //tabBar.setTitle("Home");
        new AudioDao().execute();
        bottomNavigationView.setOnNavigationItemSelectedListener(new onNavigatoritemSelectListener());
        HomeFragment home = new HomeFragment();
        loadFragment(home);
        context = this;
    }

    public void loadFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container,fragment);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.disallowAddToBackStack();
        fragmentTransaction.commit();
    }


    class onNavigatoritemSelectListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()){
                case R.id.item_chat:
                    /*if(!isCurrentFragment.equals("Progressive")){
                        fragment = new FragmentProgressive();
                        loadFragment(fragment);
                        return true;
                    }*/
                    if(!isCurrentFragment.equals("Chat")){
                        fragment = new ChatFragment();
                        loadFragment(fragment);
                        return true;
                    }
                    return false;
                case R.id.item_profile:
                    if(!isCurrentFragment.equals("Profile")){
                        fragment = new ProfileFragment();
                        //tabBar.setTitle("Profile");
                        loadFragment(fragment);
                        return true;
                    }
                    return false;
                case R.id.item_home:
                    //tabBar.setTitle("Home");
                    if(!isCurrentFragment.equals("HOME")){
                        loadFragment(new HomeFragment());
                    }
                    return true;
                case R.id.item_learnig_topic:
                    //tabBar.setTitle("Learning by topic");
                    if(!isCurrentFragment.equals("TOPIC")){
                        loadFragment(new TopicFragment());
                    }
                    return true;
                case R.id.item_audio:
                    //tabBar.setTitle("Audio");
                    if(!isCurrentFragment.equals("AUDIO")){
                        loadFragment(new AudioFragment());
                    }
                    return true;
            }
            return false;
        }
    }

    public static void setThemeFragment(int theme){
        context.setTheme(theme);
    }
}
