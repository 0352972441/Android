package com.example.assignmentandroid.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.assignmentandroid.R;
import com.example.assignmentandroid.ui.coursefragment.MyCourseFragment;
import com.example.assignmentandroid.ui.registerfragment.RegistrationFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CourseActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    public static String isFragmentCurrent = "mycourse";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigator);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.my_course :
                        if(!isFragmentCurrent.equals("mycourse")) {
                            loadFragment(new MyCourseFragment());
                        }
                        return true;
                    case R.id.register:
                        if(!isFragmentCurrent.equals("registration")) {
                            loadFragment(new RegistrationFragment());
                        }
                        return true;
                    case R.id.schedule:
                        if(!isFragmentCurrent.equals("schedule")) {
                            loadFragment(new MyCourseFragment());
                        }
                        return true;
                    case R.id.test_schedule:
                        if(!isFragmentCurrent.equals("testschedule")) {
                            loadFragment(new MyCourseFragment());
                        }
                        return true;
                }
                return false;
            }
        });
    }

    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_fragment,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
