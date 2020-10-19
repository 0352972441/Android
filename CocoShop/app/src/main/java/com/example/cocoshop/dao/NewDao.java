package com.example.cocoshop.dao;

import android.net.Uri;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocoshop.Adapter.audioadapter.CardItemAudioPopularAdapter;
import com.example.cocoshop.Models.audiomodels.Audio;
import com.example.cocoshop.Models.audiomodels.Category;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NewDao extends AsyncTask<Void, ArrayList<Audio>,Void> {
    private static final FirebaseStorage store;
    private RecyclerView newRecyclerView;
    public ArrayList<Audio> listAllData;
    static {
        store = FirebaseStorage.getInstance();
    }

    public NewDao(RecyclerView newRecyclerView) {
        this.newRecyclerView = newRecyclerView;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        store.getReference().child("audios").listAll().addOnCompleteListener(new OnCompleteListener<ListResult>() {
            @Override
            public void onComplete(@NonNull Task<ListResult> task) {
                if(task.isSuccessful()){
                    for(int i=0; i< 5; i++){
                        StorageReference item =  task.getResult().getItems().get(i);
                        final String name = (String) item.getName().subSequence(0,item.getName().length() - 4);
                        listAllData = new ArrayList<>();
                        item.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                listAllData.add(new Audio("Unknow","Anana",new com.example.cocoshop.Models.audiomodels.Sound(name,task.getResult().toString()),2, Category.MUSIC));
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
            CardItemAudioPopularAdapter audioPopularAdapter = new CardItemAudioPopularAdapter(values[0]);
            newRecyclerView.setAdapter(audioPopularAdapter);
            newRecyclerView.setLayoutManager(new LinearLayoutManager(newRecyclerView.getContext(),RecyclerView.VERTICAL,false));
        }
    }
}