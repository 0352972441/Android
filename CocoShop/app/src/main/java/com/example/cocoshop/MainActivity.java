package com.example.cocoshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cocoshop.adapter.ViewPagerOnboarding;
import com.example.cocoshop.screen.authscreen.LoginScreen;
import com.example.cocoshop.fragment.Onboarding.OnboardingOne;
import com.example.cocoshop.fragment.Onboarding.OnboardingThree;
import com.example.cocoshop.fragment.Onboarding.OnboardingTwo;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 mViewPager;
    private ViewPagerOnboarding adapter;
    private TextView mtxSkip,mtxNext,mtxGetStart;
    private LinearLayout linearLayoutIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager2)findViewById(R.id.viewPageOnboarding);
        mtxSkip = (TextView)findViewById(R.id.txSkip);
        mtxNext = (TextView)findViewById(R.id.mtxNext);
        mtxGetStart = (TextView)findViewById(R.id.mtxGetStart);
        linearLayoutIndicator = (LinearLayout)findViewById(R.id.indicator);
        adapter = new ViewPagerOnboarding(this);
        adapter.addFragment(new OnboardingOne());
        adapter.addFragment(new OnboardingTwo());
        adapter.addFragment(new OnboardingThree());
        mViewPager.setAdapter(adapter);
        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                skipPage(position);
                changeNext(position);
                setIndicator(position);
            }
        });
        skipAllPager();
        nextPage();
        setUpIndicator();
        setIndicator(0);
    }

    private void skipPage(int position){
        if(position ==0){
            mtxSkip.setVisibility(View.VISIBLE);
        }else{
            mtxSkip.setVisibility(View.INVISIBLE);
        }
    }

    private void skipAllPager(){
        mtxSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(adapter.listFragment.size()-1);
            }
        });
    }

    private void changeNext(int position){
        if(adapter.listFragment.size()-1 == position){
            //mtxNext.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            mtxNext.setVisibility(View.INVISIBLE);
            mtxGetStart.setVisibility(View.VISIBLE);
            mtxGetStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, LoginScreen.class);
                    startActivity(intent);
                    finish();
                }
            });
        }else{
            mtxNext.setVisibility(View.VISIBLE);
            mtxGetStart.setVisibility(View.INVISIBLE);
        }
    }

    private void nextPage(){
        mtxNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mViewPager.getCurrentItem() == adapter.listFragment.size()-1){
                }else{
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1);
                }
            }
        });
    }

    private void setUpIndicator(){
        ImageView[] indicator = new ImageView[adapter.listFragment.size()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(8,0,8,0);
        for(int i=0; i< adapter.listFragment.size(); i++){
            indicator[i] = new ImageView(getApplicationContext());
            indicator[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.indicator));
            indicator[i].setLayoutParams(layoutParams);
            linearLayoutIndicator.addView(indicator[i]);
        }
    }

    private void setIndicator(int posotion){
        for(int i=0; i< linearLayoutIndicator.getChildCount(); i++){
            ImageView indicator = (ImageView) linearLayoutIndicator.getChildAt(i);
            if(i==posotion){
                indicator.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.unindicator));
            }else{
                indicator.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.indicator));
            }
        }
    }
}
