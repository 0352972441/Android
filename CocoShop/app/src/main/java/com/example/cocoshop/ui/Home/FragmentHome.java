package com.example.cocoshop.ui.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import com.example.cocoshop.Adapter.CardItemAdapter;
import com.example.cocoshop.Adapter.HomeNewApdapter;
import com.example.cocoshop.Models.Card;
import com.example.cocoshop.Models.Topic;
import com.example.cocoshop.R;

import java.util.ArrayList;


public class FragmentHome extends Fragment {
    private RecyclerView cardItemRecycler;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cardItemRecycler = (RecyclerView)view.findViewById(R.id.cardItem);
        CardItemAdapter adapter = new CardItemAdapter(createUiCardItem());
        cardItemRecycler.setAdapter(adapter);
        cardItemRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    private ArrayList createUiCardItem(){
        ArrayList data = new ArrayList<>();
        data.add(new Card(R.drawable.ic_notifications_none_grey_24dp,"Audio","5",R.drawable.background_card_item));
        data.add(new Card(R.drawable.ic_notifications_none_grey_24dp,"Home","5",R.drawable.background_card_item));
        data.add(new Card(R.drawable.ic_notifications_none_grey_24dp,"Profile","5",R.drawable.background_card_item));
        data.add(new Card(R.drawable.ic_notifications_none_grey_24dp,"Progressive","5",R.drawable.background_card_item));
        return data;
    }
}
