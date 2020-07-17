package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import com.example.myapplication.ActivityFragment.OneBlankFragment;
import com.example.myapplication.ActivityFragment.ThreeFragment;
import com.example.myapplication.ActivityFragment.TwoFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noName no = new noName(getSupportFragmentManager());
        no.addFragment(new OneBlankFragment(),"Home");
        no.addFragment(new TwoFragment(),"COPYRIGHT");
        no.addFragment(new ThreeFragment(),"AUTHOR");
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(no);
        TranformPager tranformPager = new TranformPager();
        viewPager.setPageTransformer(true,tranformPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
       /* FragmentManager fragment = getSupportFragmentManager();
        fragment.beginTransaction().replace(R.id.fragmendown,twoFragment,twoFragment.getTag()).commit();

        OneBlankFragment oneBlankFragment = new OneBlankFragment();
        FragmentManager fragment1 = getSupportFragmentManager();
        fragment1.beginTransaction().replace(R.id.fragmenup,oneBlankFragment,oneBlankFragment.getTag()).commit();*/

    }

    private class noName extends FragmentPagerAdapter{
        private final ArrayList<Fragment> listFragment = new ArrayList<>();
        private final ArrayList<String> listTitle = new ArrayList<>();
        public noName(FragmentManager fragment){
            super(fragment);
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return listFragment.get(position);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            return listTitle.get(position);
        }

        @Override
        public int getCount() {
            return listFragment.size();
        }

        public void addFragment(Fragment fragment, String title){
            listTitle.add(title);
            listFragment.add(fragment);
        }

    }

    class TranformPager implements ViewPager.PageTransformer{
        private static final float MIN_SCALE = 0.8f;
        private static final float  MIN_ALPHA = 0.7f;
        @Override
        public void transformPage(@NonNull View page, float position) {
            int pageWidth = page.getWidth();
            int pageHeight = page.getHeight();
            if(position < -1){
                page.setAlpha(0f);
            }else if(position <= 1){
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    page.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    page.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                page.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));
            }else{
                page.setAlpha(0f);
            }
        }
    }
}
