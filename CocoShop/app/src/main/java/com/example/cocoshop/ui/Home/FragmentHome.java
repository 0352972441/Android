package com.example.cocoshop.ui.Home;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cocoshop.Adapter.CardItemAdapter;
import com.example.cocoshop.Models.CardHomeModel.Card;
import com.example.cocoshop.R;
import com.example.cocoshop.Screen.HomeScreen.HomeScreen;
import com.example.cocoshop.dao.LoadAvataDao;
import com.example.cocoshop.dao.NewDao;
import com.example.cocoshop.dao.PopularDao;
import com.example.cocoshop.dao.RecommendDao;
import com.example.cocoshop.listener.Listener;

import java.util.ArrayList;


public class FragmentHome extends Fragment {
    private RecyclerView recommendRecyclerView,popularRecyclerView,newRecyclerView;
    private ImageView imgAvata;
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

        recommendDao = new RecommendDao(recommendRecyclerView);recommendDao.execute();
        popularDao = new PopularDao(popularRecyclerView);popularDao.execute();
        newDao = new NewDao(newRecyclerView);newDao.execute();
        new LoadAvataDao(imgAvata).execute();
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
