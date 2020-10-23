package com.example.cocoshop.dao;

import android.content.Intent;
import android.os.AsyncTask;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocoshop.adapter.audioadapter.CardItemAudioPopularAdapter;
import com.example.cocoshop.listener.Listener;
import com.example.cocoshop.models.audiomodels.Audio;
import com.example.cocoshop.models.audiomodels.Category;
import com.example.cocoshop.screen.audioscreen.PlayAudioActivity;

import java.util.ArrayList;
import java.util.List;

public class ViewAllAudioDao extends AsyncTask<String, List<Audio>,Void> {
    private RecyclerView itemAudioRecycler;
    private List<Audio> data;
    public ViewAllAudioDao(RecyclerView itemAudioRecycler) {
        this.itemAudioRecycler = itemAudioRecycler;
    }

    @Override
    protected Void doInBackground(String... category) {
        data = new ArrayList<>();
        if(Category.ALL != Category.valueOf(category[0])){
            for(Audio i : AudioDao.listAllData){
                if(i.getCategory().toString().equalsIgnoreCase(category[0])){
                    data.add(i);
                }
            }
        }else {
            data = AudioDao.listAllData;
        }
        publishProgress(data);
        return null;
    }

    @Override
    protected void onProgressUpdate(List<Audio>... values) {
        super.onProgressUpdate(values);
        if(values[0] != null){
            CardItemAudioPopularAdapter audioPopularAdapter = new CardItemAudioPopularAdapter(data);
            itemAudioRecycler.setAdapter(audioPopularAdapter);
            itemAudioRecycler.setLayoutManager(new LinearLayoutManager(itemAudioRecycler.getContext(),RecyclerView.VERTICAL,false));
            audioPopularAdapter.setPlayAudioListener(new Listener() {
                @Override
                public void listener(int position) {
                    Intent intent = new Intent(itemAudioRecycler.getContext(), PlayAudioActivity.class);
                    Audio audio = data.get(position);
                    intent.putExtra(PlayAudioActivity.KEYAUDIO, BundleData.sendData(audio));
                    itemAudioRecycler.getContext().startActivity(intent);
                }
            });
        }
    }
}
