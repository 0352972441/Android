package com.example.cocoshop.dao;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocoshop.adapter.audioadapter.CardItemAudioApdapter;
import com.example.cocoshop.adapter.audioadapter.CardItemAudioPopularAdapter;
import com.example.cocoshop.models.audiomodels.Audio;
import com.example.cocoshop.models.audiomodels.Category;
import com.example.cocoshop.screen.audioscreen.PlayAudioActivity;
import com.example.cocoshop.listener.Listener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class AudioDao extends AsyncTask<Void,ArrayList<Audio>,Void> {
    public  static ArrayList<Audio> listAllData = new ArrayList<>();
    private static final FirebaseStorage storage;
    private static final StorageReference mRef;
    private RecyclerView popularAudioRecyclerView;
    private RecyclerView recommendAudioRecyclerView;
    private CardItemAudioPopularAdapter popularAudioAdapter;
    private CardItemAudioApdapter recommendAudioApdapter;
    private ArrayList<Audio> listAudio;
    private boolean isCreate = false;
    public AudioDao(RecyclerView popularAudioRecyclerView, RecyclerView recommendAudioRecyclerView) {
        this.popularAudioRecyclerView = popularAudioRecyclerView;
        this.recommendAudioRecyclerView = recommendAudioRecyclerView;
    }

    public AudioDao() {
        isCreate = true;
    }

    static {
        storage = FirebaseStorage.getInstance();
        mRef = storage.getReference();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        StorageReference audios = mRef.child("audios");
        audios.listAll().addOnCompleteListener(new OnCompleteListener<ListResult>() {
            @Override
            public void onComplete(@NonNull Task<ListResult> task) {
                if(task.isSuccessful()){
                    listAllData.clear();// Clear
                    for (final StorageReference item : task.getResult().getItems()) {
                        final String name = (String) item.getName().subSequence(0,item.getName().length() - 4);
                        item.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                listAllData.add(new Audio("Unknow","Anana",new com.example.cocoshop.models.audiomodels.Sound(name,task.getResult().toString()),2,Category.MUSIC));
                                publishProgress(listAllData);
                            }
                        });
                    }
                }
            }
        });
        return null;
    }

    @Override
    protected void onProgressUpdate(ArrayList<Audio>... values) {
        super.onProgressUpdate(values);
        if(values[0] != null){
            if(!isCreate){
                listAudio = new ArrayList<>();
                for(int i=0; i< listAllData.size() - (listAllData.size() /3); i++){
                    listAudio.add(listAllData.get(i));
                }
                popularAudioAdapter = new CardItemAudioPopularAdapter(listAudio);
                recommendAudioApdapter = new CardItemAudioApdapter(listAllData);

                recommendAudioRecyclerView.setAdapter(recommendAudioApdapter);
                recommendAudioRecyclerView.setLayoutManager(new LinearLayoutManager(recommendAudioRecyclerView.getContext(),RecyclerView.HORIZONTAL,false));
                popularAudioRecyclerView.setAdapter(popularAudioAdapter);
                popularAudioRecyclerView.setLayoutManager(new LinearLayoutManager(popularAudioRecyclerView.getContext(),LinearLayoutManager.VERTICAL,false));
                onClickPlayAudio();
            }
        }
    }

    private void onClickPlayAudio(){
        final Intent intent = new Intent(popularAudioRecyclerView.getContext(), PlayAudioActivity.class);
        popularAudioAdapter.setPlayAudioListener(new Listener() {
            @Override
            public void listener(int position) {
                Audio audio = listAudio.get(position);
                intent.putExtra(PlayAudioActivity.KEYAUDIO, BundleData.sendData(audio));
                popularAudioRecyclerView.getContext().startActivity(intent);
            }
        });
        recommendAudioApdapter.setPlayAudioListener(new Listener() {
            @Override
            public void listener(int position) {
                //Bundle bundle = new Bundle();
                Audio audio = listAllData.get(position);
                intent.putExtra(PlayAudioActivity.KEYAUDIO, BundleData.sendData(audio));
                recommendAudioRecyclerView.getContext().startActivity(intent);
            }
        });
    }

    /*public static ArrayList<Audio> getAllAudioPopular(){
        final ArrayList<Audio> listAudio = new ArrayList<>();
        for(int i=0; i< listAllData.size() - (listAllData.size() /3); i++){
            listAudio.add(listAllData.get(i));
        }
        return listAudio;
    }*/


   /* public static void callAudio(){
        //final ArrayList<com.example.cocoshop.Models.audiomodels.Sound> listSound = new ArrayList<>();
        StorageReference audios = mRef.child("audios");
        audios.listAll().addOnCompleteListener(new OnCompleteListener<ListResult>() {
            @Override
            public void onComplete(@NonNull Task<ListResult> task) {
                if(task.isSuccessful()){
                    for (final StorageReference item : task.getResult().getItems()) {
                        final String name = (String) item.getName().subSequence(0,item.getName().length() - 4);
                        item.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                //Log.d("Tag", "Url : " +task.getResult());
                                listAllData.add(new Audio("Unknow","Anana",new com.example.cocoshop.Models.audiomodels.Sound(name,task.getResult().toString()),2,Category.MUSIC));
                            }
                        });
                    }
                }
            }
        });
    }*/
}
