package com.example.lab8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.lab8.ui.Activity.FirstActivity;
import com.example.lab8.ui.Fragment.FirstFragment;
import com.example.lab8.ui.Fragment.SecondsFragmen;
import com.example.lab8.ui.Fragment.ThridFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    NavigationView mNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavigation = (NavigationView)findViewById(R.id.mNavigation);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        createToggle(toolbar);
        mNavigation.setNavigationItemSelectedListener(this);
    }

    private void createToggle(Toolbar toolbar){
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.draw_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Fragment fragment = null;
        Intent intent = null;
        switch (id){
            case R.id.first: fragment = new FirstFragment();break;
            case R.id.second: fragment = new SecondsFragmen();break;
            case R.id.thrid: fragment = new ThridFragment();break;
            case R.id.ac_first: intent = new Intent(this, FirstActivity.class);break;
            case R.id.ac_second: intent = new Intent(this, FirstActivity.class);break;
            default:
                break;
        }
        if(fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame,fragment);
            fragmentTransaction.commit();
        }else{
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.draw_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.draw_layout);
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}
