package com.example.cocoshop.fireStore;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.cocoshop.Models.UserAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FireStoreUser {
    private static final FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private static final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    private static Map<String, Object> user = new HashMap<>();
    public static void addUser(String account,String email, String avata){
        user.put("account",account);
        user.put("email",email);
        user.put("avata",avata);
        firestore.collection("users").document(currentUser.getUid()).set(user);
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
