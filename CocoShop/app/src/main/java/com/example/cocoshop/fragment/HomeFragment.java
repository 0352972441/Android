package com.example.cocoshop.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cocoshop.R;
import com.example.cocoshop.adapter.audioadapter.CardItemAudioPopularAdapter;
import com.example.cocoshop.dao.AudioDao;
import com.example.cocoshop.models.audiomodels.Audio;
import com.example.cocoshop.models.audiomodels.Sound;
import com.example.cocoshop.screen.HomeScreen;
import com.example.cocoshop.dao.LoadAvataDao;
import com.example.cocoshop.dao.NewDao;
import com.example.cocoshop.dao.PopularDao;
import com.example.cocoshop.dao.RecommendDao;
import com.example.cocoshop.screen.SearchActivity;

import java.util.ArrayList;

import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.BaseSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.SearchResultListener;


public class HomeFragment extends Fragment {
    private RecyclerView recommendRecyclerView,popularRecyclerView,newRecyclerView;
    private ImageView imgAvata;
    private EditText edSearch;
    public static RecommendDao recommendDao;
    public static PopularDao popularDao;
    public static NewDao newDao;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recommendRecyclerView = (RecyclerView)view.findViewById(R.id.recommend_recycler_view);
        popularRecyclerView = (RecyclerView)view.findViewById(R.id.popular_recycler_view);
        newRecyclerView = (RecyclerView)view.findViewById(R.id.new_recycler_view);
        imgAvata = (ImageView)view.findViewById(R.id.avata_home_fragment);
        edSearch = view.findViewById(R.id.ed_search);

        recommendDao = new RecommendDao(recommendRecyclerView);recommendDao.execute();
        popularDao = new PopularDao(popularRecyclerView);popularDao.execute();
        newDao = new NewDao(newRecyclerView);newDao.execute();
        new LoadAvataDao(imgAvata).execute();
        onClickSearch();
    }


    private void onClickSearch(){
        edSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edSearch.getText().toString().isEmpty()){
                    return;
                }else{
                    Intent intentSearchResults = new Intent(getContext(), SearchActivity.class);
                    intentSearchResults.putExtra("search",edSearch.getText().toString());
                    startActivity(intentSearchResults);
                    edSearch.setText("");
                }
            }
        });
        edSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    edSearch.setText("");
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        HomeScreen.isCurrentFragment = "HOME";
    }
}
