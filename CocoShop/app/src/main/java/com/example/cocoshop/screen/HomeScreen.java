package com.example.cocoshop.screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import com.agrawalsuneet.dotsloader.loaders.CircularDotsLoader;
import com.example.cocoshop.R;
import com.example.cocoshop.dao.AudioDao;
import com.example.cocoshop.fragment.TopicFragment;
import com.example.cocoshop.fragment.ChatFragment;
import com.example.cocoshop.fragment.AudioFragment;
import com.example.cocoshop.fragment.ProfileFragment;
import com.example.cocoshop.fragment.HomeFragment;
import com.example.cocoshop.permission.InternetPermission;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeScreen extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    public static String isCurrentFragment = "";
    private CircularDotsLoader mCircularDotsLoader;
    //private ActionBar tabBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavigatorView);
        mCircularDotsLoader = findViewById(R.id.progress_circular);
        bottomNavigationView.setItemIconTintList(null);
        //tabBar = getSupportActionBar();
        //tabBar.setTitle("Home");
        new AudioDao().execute();
        bottomNavigationView.setOnNavigationItemSelectedListener(new onNavigatoritemSelectListener());
        HomeFragment home = new HomeFragment();
        loadFragment(home);

        // Kiểm tra kết nối internet
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                InternetPermission internetPermission = new InternetPermission(HomeScreen.this);
                internetPermission.run();
                if(internetPermission.isOccurred()){
                    mCircularDotsLoader.setVisibility(View.VISIBLE);
                }else{
                    mCircularDotsLoader.setVisibility(View.INVISIBLE);
                }
                handler.postDelayed(this,3000);
            }
        });
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

}
