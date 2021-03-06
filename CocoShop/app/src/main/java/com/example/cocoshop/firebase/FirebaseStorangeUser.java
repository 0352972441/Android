package com.example.cocoshop.firebase;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class FirebaseStorangeUser extends AsyncTask<Void, Void,Boolean> {
    private static final FirebaseAuth mAuth;
    private  StorageReference storeRef;
    private static final FirebaseFirestore firestore;
    static {
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
    }
    private Uri filePath;
    private Context context;
    public FirebaseStorangeUser(Uri filePath,StorageReference storeRef,Context context) {
        this.filePath = filePath;
        this.storeRef = storeRef;
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        StorageReference ref =  storeRef.child("avatas").child(mAuth.getCurrentUser().getUid());
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(),filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,10,baos);
        byte[] bytes = baos.toByteArray();
        if(filePath!= null){
            ref.putBytes(bytes).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
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
