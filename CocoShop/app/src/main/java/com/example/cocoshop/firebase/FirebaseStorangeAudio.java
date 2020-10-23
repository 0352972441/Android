package com.example.cocoshop.firebase;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.cocoshop.models.audiomodels.Sound;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class FirebaseStorangeAudio {
    private static final FirebaseStorage storage;
    private static final StorageReference mRef;
    static {
        storage = FirebaseStorage.getInstance();
        mRef = storage.getReference();
    }
    public static ArrayList<Sound> callAudio(){
        final ArrayList<Sound> listSound = new ArrayList<>();
        StorageReference audios = mRef.child("audios");
        audios.listAll().addOnCompleteListener(new OnCompleteListener<ListResult>() {
            @Override
            public void onComplete(@NonNull Task<ListResult> task) {
                if(task.isSuccessful()){
                    for (final StorageReference item : task.getResult().getItems()) {
                        item.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                Log.d("Tag", "Url : " +task.getResult());
                                listSound.add(new Sound(item.getName(),task.getResult().toString()));
                            }
                        });
                    }
                }
            }
        });
        return listSound;
    }
}
