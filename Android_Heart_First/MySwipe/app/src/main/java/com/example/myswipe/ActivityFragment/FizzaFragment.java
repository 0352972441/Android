package com.example.myswipe.ActivityFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myswipe.Activity.Activity_Detail;
import com.example.myswipe.Data.CaptionedImagesAdapter;
import com.example.myswipe.Data.Pizza;
import com.example.myswipe.Listenner.Listener;
import com.example.myswipe.R;
import java.util.ArrayList;

public class FizzaFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_pizza_fragment,container,false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.pizza_fragment);
        Pizza pizza = new Pizza("Pizza Ytalia",R.drawable.diavolo);
        Pizza pizzaFrance = new Pizza("Pizza France",R.drawable.funghi);
        ArrayList<Pizza> listData = new ArrayList<>();
        listData.add(pizza);
        listData.add(pizzaFrance);
        Pizza.setListData(listData);
        CaptionedImagesAdapter captionedImagesAdapter = new CaptionedImagesAdapter(listData);
        recyclerView.setAdapter(captionedImagesAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        captionedImagesAdapter.setListener(new Listener() {
            @Override
            public void onclick(int position) {
                Intent intent = new Intent(getActivity(),Activity_Detail.class);
                intent.putExtra(Activity_Detail.EXTRA,position);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

}
