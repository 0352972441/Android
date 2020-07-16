package com.example.myswipe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.myswipe.Activity.ShowDetail;
import com.example.myswipe.ActivityFragment.AddressFragment;
import com.example.myswipe.ActivityFragment.FizzaFragment;
import com.example.myswipe.ActivityFragment.PastaFragment;
import com.example.myswipe.ActivityFragment.StoreFragment;
import com.example.myswipe.ActivityFragment.Top_Fragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    ShareActionProvider shareActionProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SelectedPagerAdaper selectedPagerAdaper = new SelectedPagerAdaper(getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(selectedPagerAdaper);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);
        /*TabLayout tabLayout1 = (TabLayout) findViewById(R.id.tab1);
        tabLayout1.setupWithViewPager(viewPager);*/
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareActionProvider();
        return super.onCreateOptionsMenu(menu);
    }

    public void setShareActionProvider(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(ShowDetail.EXTRA, "Hello");
        shareActionProvider.setShareIntent(intent);
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                Intent intent = new Intent(this, ShowDetail.class);startActivity(intent);return true;
            default:return super.onOptionsItemSelected(item);
        }
    }

    private class SelectedPagerAdaper extends FragmentPagerAdapter{
        public SelectedPagerAdaper(FragmentManager manager){
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: return  new Top_Fragment();
                case 1:  return  new FizzaFragment();
                case 2:  return  new PastaFragment();
                case 3:  return  new StoreFragment();
                case 4: return new AddressFragment();
            }
            return null;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0: return getResources().getText(R.string.home_tab);
                case 1: return getResources().getText(R.string.pizza_tab);
                case 2: return getResources().getText(R.string.pasta_tab);
                case 3: return getResources().getText(R.string.store_tab);
                case 4: return getResources().getText(R.string.address);
            }
            return super.getPageTitle(position);
        }

        @Override
        public int getCount() {
            return 5;
        }
    }

}
