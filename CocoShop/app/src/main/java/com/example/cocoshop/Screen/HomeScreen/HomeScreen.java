package com.example.cocoshop.Screen.HomeScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cocoshop.Models.topicsmodel.Levels;
import com.example.cocoshop.Models.topicsmodel.Topic;
import com.example.cocoshop.Models.vocabularysmodel.Vocabulary;
import com.example.cocoshop.R;
import com.example.cocoshop.dao.audiodao.Sound;
import com.example.cocoshop.dao.audiodao.TopicDao;
import com.example.cocoshop.ui.Learnbytopic.FragmentLearningTopic;
import com.example.cocoshop.ui.Progressivelearning.FragmentChat;
import com.example.cocoshop.ui.Progressivelearning.FragmentProgressive;
import com.example.cocoshop.ui.Audio.FragmentAudio;
import com.example.cocoshop.ui.Profile.FragmentProfile;
import com.example.cocoshop.ui.Home.FragmentHome;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeScreen extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    public static String isCurrentFragment = "";
    //private ActionBar tabBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        new Sound().execute();
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavigatorView);
        bottomNavigationView.setItemIconTintList(null);
        //tabBar = getSupportActionBar();
        //tabBar.setTitle("Home");
        bottomNavigationView.setOnNavigationItemSelectedListener(new onNavigatoritemSelectListener());
        FragmentHome home = new FragmentHome();
        loadFragment(home);
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
                case R.id.itemProgressive:
                    /*if(!isCurrentFragment.equals("Progressive")){
                        fragment = new FragmentProgressive();
                        loadFragment(fragment);
                        return true;
                    }*/
                    if(!isCurrentFragment.equals("Chat")){
                        fragment = new FragmentChat();
                        loadFragment(fragment);
                        return true;
                    }
                    return false;
                case R.id.itemProfile:
                    if(!isCurrentFragment.equals("Profile")){
                        fragment = new FragmentProfile();
                        //tabBar.setTitle("Profile");
                        loadFragment(fragment);
                        return true;
                    }
                    return false;
                case R.id.itemHome:
                    //tabBar.setTitle("Home");
                    if(!isCurrentFragment.equals("HOME")){
                        loadFragment(new FragmentHome());
                    }
                    return true;
                case R.id.itemLearnigTopic:
                    //tabBar.setTitle("Learning by topic");
                    if(!isCurrentFragment.equals("TOPIC")){
                        loadFragment(new FragmentLearningTopic());
                    }
                    return true;
                case R.id.itemAudio:
                    //tabBar.setTitle("Audio");
                    if(!isCurrentFragment.equals("AUDIO")){
                        loadFragment(new FragmentAudio());
                    }
                    return true;
            }
            return false;
        }
    }


}
