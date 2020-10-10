package com.example.cocoshop.firebaseStorange;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.util.HashMap;

public class FirebaseStorangeUser extends AsyncTask<Void, Void,Boolean> {
    private static final FirebaseAuth mAuth;
    //private static final FirebaseStorage store;
    private  StorageReference storeRef;
    private static final FirebaseFirestore firestore;
    static {
        mAuth = FirebaseAuth.getInstance();
        //store =  FirebaseStorage.getInstance();
        //storeRef = store.getReference();
        firestore = FirebaseFirestore.getInstance();
    }
    private Uri filePath;

    public FirebaseStorangeUser(Uri filePath,StorageReference storeRef) {
        this.filePath = filePath;
        this.storeRef = storeRef;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        StorageReference ref =  storeRef.child("avatas").child(mAuth.getCurrentUser().getUid());
        if(filePath!= null){
            ref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot task) {
                    storeRef.child("avatas/"+mAuth.getCurrentUser().getUid()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            if (uri != null) {
                                HashMap<String, Object> newUser = new HashMap<>();
                                newUser.put("avata", uri.toString());
                                firestore.collection("users").document(mAuth.getCurrentUser().getUid()).update(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Log.d("OK", "ok");
                                    }
                                });
                            }
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                }
            });
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if(aBoolean) {

        }
    }
}
