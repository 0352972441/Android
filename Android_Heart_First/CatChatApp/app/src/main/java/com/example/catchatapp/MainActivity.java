package com.example.catchatapp;

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
import android.view.Gravity;
import android.view.MenuItem;

import com.example.catchatapp.ActicityFragment.DraftsFragment;
import com.example.catchatapp.ActicityFragment.InboxFragment;
import com.example.catchatapp.ActicityFragment.SentItFragment;
import com.example.catchatapp.ActicityFragment.StrashFragment;
import com.example.catchatapp.Activity.FeedBackActivity;
import com.example.catchatapp.Activity.HelpActivity;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        createToggle(toolbar);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content_frame,new InboxFragment());
        fragmentTransaction.commit();
        createListenerNavigation();
    }

    public void createListenerNavigation(){
        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void createToggle(Toolbar toolbar){
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Fragment fragment = null;
        Intent intent = null;
        switch (id){
            case R.id.nav_drafts: fragment = new DraftsFragment();break;
            case R.id.nav_trash: fragment = new StrashFragment();break;
            case R.id.nav_inbox: fragment = new InboxFragment();break;
            case R.id.nav_help: intent = new Intent(this, HelpActivity.class);break;
            case R.id.nav_feedback: intent = new Intent(this, FeedBackActivity.class);break;
            default:
                fragment = new SentItFragment();break;
        }
        if(fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame,fragment);
            fragmentTransaction.commit();
        }else{
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}
