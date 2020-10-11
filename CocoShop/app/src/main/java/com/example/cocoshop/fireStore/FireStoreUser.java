package com.example.cocoshop.fireStore;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.cocoshop.Models.UserAccount;
import com.example.cocoshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class FireStoreUser {
    private static final FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private static final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    private static final FirebaseStorage storage = FirebaseStorage.getInstance();
    private static final StorageReference ref = storage.getReference();
    private static Map<String, Object> user = new HashMap<>();
    public static void addUser(String account,String email, String avata,String uid,Context context) throws IOException {
        user.put("account",account);
        user.put("email",email);
        user.put("avata",avata);
        user.put("uid",uid);
        firestore.collection("users").document(currentUser.getUid()).set(user);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.defaultavata);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        ref.child("avatas").child(uid).putBytes(data).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                Log.d("Uploading","Successfully");
            }
        });
    }
    public static void updateUser(UserAccount userAccount,final Context context){
        user = null;
        user = new HashMap<>();
        user.put("account",userAccount.getAccount());
        user.put("email",userAccount.getEmail());
        user.put("avata",userAccount.getImage());
        DocumentReference reference = firestore.collection("users").document(currentUser.getUid());
        reference.update(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(context, "Cập nhật tài khoản thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
