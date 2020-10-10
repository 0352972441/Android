package com.example.cocoshop.dao.audiodao;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.cocoshop.Models.audiomodels.Audio;
import com.example.cocoshop.Models.audiomodels.Category;
import com.example.cocoshop.firebaseStorange.FirebaseStorangeAudio;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class Sound extends AsyncTask<Void,ArrayList<Audio>,Void> {
    public static ArrayList<Audio> listAllData = new ArrayList<>();
    private static final FirebaseStorage storage;
    private static final StorageReference mRef;
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
                    for (final StorageReference item : task.getResult().getItems()) {
                        final String name = (String) item.getName().subSequence(0,item.getName().length() - 4);
                        item.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                listAllData.add(new Audio("Unknow","Anana",new com.example.cocoshop.Models.audiomodels.Sound(name,task.getResult().toString()),2,Category.MUSIC));
                                publishProgress(listAllData);
                            }
                        });
                    }
                }
            }
        });
        return null;
    }

    /*public static ArrayList<Audio> getAllAudio(){
            final ArrayList<Audio> listAudio = new ArrayList<>();
            for(com.example.cocoshop.Models.audiomodels.Sound i : FirebaseStorangeAudio.callAudio()){
                listAudio.add(new Audio("null","Anana",i,2, Category.MUSIC));
            }
            return listAudio;
        }*/
    public static ArrayList<Audio> getAllAudioPopular(){
        final ArrayList<Audio> listAudio = new ArrayList<>();
        for(int i=0; i< listAllData.size() - (listAllData.size() /3); i++){
            listAudio.add(listAllData.get(i));
        }
        return listAudio;
    }


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
