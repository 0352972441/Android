package com.example.cocoshop.firebaseStorange;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class FirebaseStorangeUser {
    private static final FirebaseAuth mAuth;
    private static final FirebaseStorage store;
    private static final StorageReference storeRef;
    static {
        mAuth = FirebaseAuth.getInstance();
        store =  FirebaseStorage.getInstance();
        storeRef = store.getReference();
    }


    public static void putImageAvata(Uri filePath, final Context context){
       StorageReference ref =  storeRef.child("avatas").child(mAuth.getCurrentUser().getUid());
       if(filePath!= null){
           final ProgressDialog progressDialog = new ProgressDialog(context);
           progressDialog.setTitle("Uploading...");
           progressDialog.show();
           ref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
               @Override
               public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                   progressDialog.dismiss();
                   Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();
               }
           }).addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
                   progressDialog.dismiss();
                   Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
               }
           }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
               @Override
               public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                   double progress
                           = (100.0
                           * snapshot.getBytesTransferred()
                           / snapshot.getTotalByteCount());
                   progressDialog.setMessage(
                           "Uploaded "
                                   + (int)progress + "%");
               }
           });
       }
    }

}
