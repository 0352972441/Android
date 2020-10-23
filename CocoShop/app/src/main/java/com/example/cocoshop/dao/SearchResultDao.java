package com.example.cocoshop.dao;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocoshop.adapter.audioadapter.CardItemAudioPopularAdapter;
import com.example.cocoshop.listener.Listener;
import com.example.cocoshop.models.audiomodels.Audio;
import com.example.cocoshop.screen.audioscreen.PlayAudioActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchResultDao extends AsyncTask<String, List<Audio>,Void> {
    //private AudioDao audioDao;
    private RecyclerView searchResultRecycler;
    CardItemAudioPopularAdapter cardItemAudioPopularAdapter;
    private List<Audio> data;
    private List<Audio> list;
    public SearchResultDao(RecyclerView searchResultRecycler) {
        this.searchResultRecycler = searchResultRecycler;
    }

    @SuppressLint("WrongThread")
    @Override
    protected Void doInBackground(String... strings) {
        //audioDao = new AudioDao();
        data = new ArrayList<>();
        for(Audio i : AudioDao.listAllData){
            if(i.getSound().getTitle().contains(strings[0])){
                data.add(i);
            }
        }
        onProgressUpdate(data);
        return null;
    }

    @Override
    protected void onProgressUpdate(final List<Audio>... values) {
        super.onProgressUpdate(values);
        if(values[0] != null){
            cardItemAudioPopularAdapter = new CardItemAudioPopularAdapter(values[0]);
            searchResultRecycler.setAdapter(cardItemAudioPopularAdapter);
            searchResultRecycler.setLayoutManager(new LinearLayoutManager(searchResultRecycler.getContext(),RecyclerView.VERTICAL,false));
            cardItemAudioPopularAdapter.setPlayAudioListener(new Listener() {
                @Override
                public void listener(int position) {
                    Intent intent = new Intent(searchResultRecycler.getContext(),PlayAudioActivity.class);
                    Audio audio = values[0].get(position);
                    intent.putExtra(PlayAudioActivity.KEYAUDIO, BundleData.sendData(audio));
                    searchResultRecycler.getContext().startActivity(intent);
                }
            });
        }
    }

    public void search(String name){
        list = new ArrayList<>();
        for(Audio i : AudioDao.listAllData){
            if(i.getSound().getTitle().contains(name)){
                list.add(i);
            }
        }
        cardItemAudioPopularAdapter = new CardItemAudioPopularAdapter(list);
        searchResultRecycler.setAdapter(cardItemAudioPopularAdapter);
        searchResultRecycler.setLayoutManager(new LinearLayoutManager(searchResultRecycler.getContext(),RecyclerView.VERTICAL,false));
        cardItemAudioPopularAdapter.setPlayAudioListener(new Listener() {
            @Override
            public void listener(int position) {
                Intent intent = new Intent(searchResultRecycler.getContext(),PlayAudioActivity.class);
                Audio audio = list.get(position);
                intent.putExtra(PlayAudioActivity.KEYAUDIO, BundleData.sendData(audio));
                searchResultRecycler.getContext().startActivity(intent);
            }
        });
    }
}
