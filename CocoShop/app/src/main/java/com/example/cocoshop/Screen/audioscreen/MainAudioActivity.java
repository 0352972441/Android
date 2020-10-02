package com.example.cocoshop.Screen.audioscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.cocoshop.Adapter.audioadapter.CardItemAudioPopularAdapter;
import com.example.cocoshop.Adapter.audioadapter.ItemCategoryAudioAdapter;
import com.example.cocoshop.Models.audiomodels.Audio;
import com.example.cocoshop.Models.audiomodels.Category;
import com.example.cocoshop.R;
import com.example.cocoshop.dao.audiodao.BundleData;
import com.example.cocoshop.fireStore.FireStoreAudio;
import com.example.cocoshop.listener.Listener;

import java.util.ArrayList;

public class MainAudioActivity extends AppCompatActivity {
    private RecyclerView cardItemAudioRecycler;
    private RecyclerView itemCategoryRecycler;
    private ItemCategoryAudioAdapter categoryAdapter;
    private CardItemAudioPopularAdapter cardItemAdapter;
    private static int previousPosition = 0;
    private View viewItemCategory;
    private ArrayList<Audio> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_audio);
        cardItemAudioRecycler = (RecyclerView)findViewById(R.id.card_item_audio_recycler);
        itemCategoryRecycler = (RecyclerView)findViewById(R.id.item_category_recycler);
        categoryAdapter = new ItemCategoryAudioAdapter();
        data = data();
        cardItemAdapter = new CardItemAudioPopularAdapter(data);
        itemCategoryRecycler.setAdapter(categoryAdapter);
        itemCategoryRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        cardItemAudioRecycler.setAdapter(cardItemAdapter);
        cardItemAudioRecycler.setLayoutManager(new LinearLayoutManager(MainAudioActivity.this,LinearLayoutManager.VERTICAL,false));
        displayAudioByGenre();
        onClickPlayAudio();
    }

    private void displayAudioByGenre(){
        //final ArrayList<Audio> dataCategory = new ArrayList<>();
        categoryAdapter.setCardItemCategoryListener(new Listener() {
            @Override
            public void listener(int position) {
                RelativeLayout background;
                if(position == previousPosition){
                    if(itemCategoryRecycler.findViewHolderForAdapterPosition(position) != null){
                        viewItemCategory = itemCategoryRecycler.findViewHolderForAdapterPosition(position).itemView;
                        background = (RelativeLayout)viewItemCategory.findViewById(R.id.background_item_card_category);
                        background.setBackgroundResource(R.color.colorPrimary);
                        previousPosition = position;
                        //dataCategory.clear();
                        data.clear();
                    }
                }else{
                    if(itemCategoryRecycler.findViewHolderForAdapterPosition(previousPosition) != null){
                        viewItemCategory = itemCategoryRecycler.findViewHolderForAdapterPosition(previousPosition).itemView;
                        background = (RelativeLayout)viewItemCategory.findViewById(R.id.background_item_card_category);
                        background.setBackgroundResource(R.color.white);
                       /* viewItemCategory = itemCategoryRecycler.findViewHolderForAdapterPosition(position).itemView;
                        background = (RelativeLayout)viewItemCategory.findViewById(R.id.background_item_card_category);
                        background.setBackgroundResource(R.color.colorPrimary);
                        previousPosition = position;
                        dataCategory.clear();*/
                    }
                    viewItemCategory = itemCategoryRecycler.findViewHolderForAdapterPosition(position).itemView;
                    background = (RelativeLayout)viewItemCategory.findViewById(R.id.background_item_card_category);
                    background.setBackgroundResource(R.color.colorPrimary);
                    previousPosition = position;
                    //dataCategory.clear();
                    data.clear();
                }
                if(Category.values()[position] != Category.ALL){
                    for(Audio i : data()){
                        if(i.getCategory().equals(Category.values()[position])){
                            data.add(i);
                        }
                    }
                    /*cardItemAdapter = new CardItemAudioPopularAdapter(data);
                    cardItemAudioRecycler.setAdapter(cardItemAdapter);*/
                    cardItemAdapter.setAudioPopulars(data);
                    cardItemAdapter.notifyDataSetChanged();
                    cardItemAudioRecycler.invalidate();
                }else{
                    /*cardItemAdapter = new CardItemAudioPopularAdapter(data());
                    cardItemAudioRecycler.setAdapter(cardItemAdapter);*/
                    data = data();
                    cardItemAdapter.setAudioPopulars(data);
                    cardItemAdapter.notifyDataSetChanged();
                    cardItemAudioRecycler.invalidate();
                }
            }
        });
    }

    private void onClickPlayAudio(){
        cardItemAdapter.setPlayAudioListener(new Listener() {
            @Override
            public void listener(int position) {
                Intent intent = new Intent(MainAudioActivity.this,PlayAudioActivity.class);
                Audio audio = data.get(position);
                intent.putExtra(PlayAudioActivity.KEYAUDIO, BundleData.sendData(audio));
                startActivity(intent);
            }
        });
    }


    private ArrayList<Audio> data(){
        ArrayList<Audio> data = new ArrayList<>();
        data.add(new Audio("Hello1","Url","Hana chister","Url",R.drawable
                .background_card_item, Category.EDUCATION));
        data.add(new Audio("Gặp người nước ","Url","Hana chister","Url",R.drawable
                .background_card_item,Category.EDUCATION));
        data.add(new Audio("Gặp người nước 2","Url","Hana chister","Url",R.drawable
                .background_card_item,Category.EDUCATION));
        data.add(new Audio("Gặp người nước3","Url","Hana chister","Url",R.drawable
                .background_card_item,Category.MUSIC));
        data.add(new Audio("Hello3","Url","Hana chister","Url",R.drawable
                .background_card_item,Category.EDUCATION));
        return data;
    }
}
