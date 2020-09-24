package com.example.revenuemanagement.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.revenuemanagement.Models.Image;
import com.example.revenuemanagement.R;
import com.example.revenuemanagement.adapter.Category_Adapter;
import com.example.revenuemanagement.adapter.ItemOnListener;
import com.example.revenuemanagement.entity.ExpenditureType;
import com.example.revenuemanagement.ui.Expenditures.Expenditure;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {
    private  boolean isShopping = false;
    private  boolean isCategory = false;
    private  boolean isOutfit = false;
    private  boolean isTravelling = false;
    private ArrayList<Image> listCategory;
    private int currentImage;
    private Switch mIsShopping,mIsCategory,mIsTravelling,mIsOutfit;
    public static final String ISPURCHASE  = "purchase";
    public static final String ISCATEGORY  = "category";
    public static final String ISAD  = "ad";
    public static final String ISOUTFIT  = "outfit";
    public static final String KEYBUNDLE  = "bundle";
    public static final String CURRENTIMAGE = "image";
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        bundle = (Bundle) getIntent().getExtras().getBundle(KEYBUNDLE);
        mIsShopping = (Switch) findViewById(R.id.isShopping);
        mIsTravelling = (Switch) findViewById(R.id.isTravelling);
        mIsOutfit = (Switch) findViewById(R.id.isOutfit);
        mIsCategory = (Switch) findViewById(R.id.isCategory);
        final ImageView mImageCategory = (ImageView)findViewById(R.id.titleCategory);
        setUpFilter();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.ic_filter_list_black_24dp);
        currentImage = bundle.getInt(CURRENTIMAGE);
        if(currentImage != 0){
            mImageCategory.setImageResource(currentImage);
        }
        mImageCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View expentditure_type_category = LayoutInflater.from(SettingActivity.this).inflate(R.layout.dialog_category_expentditure_type, null, false);
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                RecyclerView recyclerView = (RecyclerView) expentditure_type_category.findViewById(R.id.reycler_dialog_expenditure_type);
                listCategory = imageCategory();
                final Category_Adapter adapter = new Category_Adapter(listCategory);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(expentditure_type_category.getContext(), 4));
                builder.setView(expentditure_type_category);
                final AlertDialog alert = builder.create();
                alert.show();
                adapter.setListener(new ItemOnListener() {
                    @Override
                    public void listener(int position) {
                        currentImage = listCategory.get(position).getImage();
                        mImageCategory.setImageResource(currentImage);
                        Expenditure.setCrurentImage(currentImage);
                        alert.dismiss();
                    }
                });
            }
        });
    }


    public void setUpFilter(){
        // 1 Shopping
        mIsShopping.setChecked(bundle.getBoolean(ISPURCHASE));
        mIsShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isCurrent = (isShopping = !isShopping);
                mIsShopping.setChecked(isCurrent);
                Expenditure.setIsPurchase(isCurrent);
                Toast.makeText(SettingActivity.this, String.valueOf(isCurrent), Toast.LENGTH_SHORT).show();
            }
        });
        // Travelling
        mIsTravelling.setChecked(bundle.getBoolean(ISAD));
        mIsTravelling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isCurrent = (isTravelling = !isTravelling);
                mIsTravelling.setChecked(isCurrent);
                Expenditure.setIsAd(isCurrent);
                Toast.makeText(SettingActivity.this, String.valueOf(isCurrent), Toast.LENGTH_SHORT).show();
            }
        });
        // Category
        mIsCategory.setChecked(bundle.getBoolean(ISCATEGORY));
        mIsCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isCurrent = (isCategory = !isCategory);
                mIsCategory.setChecked(isCurrent);
                Expenditure.setIsCategory(isCurrent);
                if(currentImage == 0){
                    currentImage = R.drawable.category_outfit;
                    Expenditure.setCrurentImage(currentImage);
                }
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

    private ArrayList<Image> imageCategory(){
        ArrayList<Image> list = new ArrayList<>();
        list.add(new Image(R.drawable.category_outfit));
        list.add(new Image(R.drawable.category_salary));
        list.add(new Image(R.drawable.category_ad));
        list.add(new Image(R.drawable.image_1));
        list.add(new Image(R.drawable.image_2));
        list.add(new Image(R.drawable.image_3));
        list.add(new Image(R.drawable.image_4));
        list.add(new Image(R.drawable.image_6));
        list.add(new Image(R.drawable.image_7));
        list.add(new Image(R.drawable.image_8));
        list.add(new Image(R.drawable.image_9));
        list.add(new Image(R.drawable.image_10));
        list.add(new Image(R.drawable.image_11));
        list.add(new Image(R.drawable.image_12));
        list.add(new Image(R.drawable.image_13));
        list.add(new Image(R.drawable.image_14));
        list.add(new Image(R.drawable.image_15));
        list.add(new Image(R.drawable.image_16));
        list.add(new Image(R.drawable.image_17));
        list.add(new Image(R.drawable.image_18));
        list.add(new Image(R.drawable.image_19));
        list.add(new Image(R.drawable.image_22));
        list.add(new Image(R.drawable.image_23));
        return list;
    }

}
