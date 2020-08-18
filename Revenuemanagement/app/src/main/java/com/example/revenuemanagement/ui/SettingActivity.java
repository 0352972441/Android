package com.example.revenuemanagement.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.example.revenuemanagement.R;
import com.example.revenuemanagement.ui.Expenditures.Expenditure;

public class SettingActivity extends AppCompatActivity {
    private  boolean isPurchase = false;
    private  boolean isSalary = false;
    private  boolean isOutfit = false;
    private  boolean isAd = false;
    private Switch mIsPurchase,mIsSalary,mIsAd,mIsOutfit;
    public static final String ISPURCHASE  = "purchase";
    public static final String ISSALARY  = "salary";
    public static final String ISAD  = "ad";
    public static final String ISOUTFIT  = "outfit";
    public static final String KEYBUNDLE  = "bundle";
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        bundle = (Bundle) getIntent().getExtras().getBundle(KEYBUNDLE);
        mIsPurchase = (Switch)findViewById(R.id.isPurchese);
        mIsAd = (Switch)findViewById(R.id.isAd);
        mIsOutfit = (Switch)findViewById(R.id.isOutfit);
        mIsSalary = (Switch)findViewById(R.id.isSalary);
        setUpFilter();
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.ic_filter_list_black_24dp);
    }

    public void setUpFilter(){
        // 1 PURCHASES
        mIsPurchase.setChecked(bundle.getBoolean(ISPURCHASE));
        mIsPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isCurrent = (isPurchase = !isPurchase);
                mIsPurchase.setChecked(isCurrent);
                Expenditure.setIsPurchase(isCurrent);
                Toast.makeText(SettingActivity.this, String.valueOf(isCurrent), Toast.LENGTH_SHORT).show();
            }
        });
        // AD
        mIsAd.setChecked(bundle.getBoolean(ISAD));
        mIsAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isCurrent = (isAd = !isAd);
                mIsAd.setChecked(isCurrent);
                Expenditure.setIsAd(isCurrent);
                Toast.makeText(SettingActivity.this, String.valueOf(isCurrent), Toast.LENGTH_SHORT).show();
            }
        });
        // SALARY
        mIsSalary.setChecked(bundle.getBoolean(ISSALARY));
        mIsSalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isCurrent = (isSalary = !isSalary);
                mIsSalary.setChecked(isCurrent);
                Expenditure.setIsSalary(isCurrent);
                Toast.makeText(SettingActivity.this, String.valueOf(isCurrent), Toast.LENGTH_SHORT).show();
            }
        });
        // OUTFIT
        mIsOutfit.setChecked(bundle.getBoolean(ISOUTFIT));
        mIsOutfit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isCurrent = (isOutfit = !isOutfit);
                mIsOutfit.setChecked(isCurrent);
                Expenditure.setIsOutfit(isCurrent);
                Toast.makeText(SettingActivity.this, String.valueOf(isCurrent), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
