package com.example.cocoshop.dao;

import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

public class LoadAvataDao extends AsyncTask<Void,Uri,Void> {
    private ImageView imgAvata;
    private static final FirebaseAuth mAuth;
    private static  final FirebaseStorage storage;
    static {
        storage = FirebaseStorage.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    public LoadAvataDao(ImageView imgAvata) {
        this.imgAvata = imgAvata;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        storage.getReference().child("avatas").child(mAuth.getCurrentUser().getUid()).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()){
                    onProgressUpdate(task.getResult());
                }
            }
        });
        return null;
    }

    @Override
    protected void onProgressUpdate(Uri... values) {
        if(values[0] != null){
            Picasso.with(imgAvata.getContext()).load(values[0]).into(imgAvata);
        }
    }
}
