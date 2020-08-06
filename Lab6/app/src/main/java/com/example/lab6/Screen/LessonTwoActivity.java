package com.example.lab6.Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.lab6.Fragment.LeftFragment;
import com.example.lab6.Fragment.RightFragment;
import com.example.lab6.R;

public class LessonTwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_two);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_frame,new RightFragment());
        fragmentTransaction.commit();
    }

    public void switchFrament(View view) {
        Fragment fragment = null;
        switch (view.getId()){
            case R.id.fragmentOne :
                fragment = new LeftFragment();
                break;
            case R.id.fragmentTwo:
                fragment = new RightFragment();
                break;
            default:
                fragment = new RightFragment();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_frame,fragment);
        fragmentTransaction.commit();
    }
}
