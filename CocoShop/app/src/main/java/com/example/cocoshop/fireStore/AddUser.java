package com.example.cocoshop.fireStore;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddUser {
    private static final FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private static final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    private static Map<String, Object> user = new HashMap<>();
    public static void addUser(String userName,String email, String avata){
        user.put("userName",userName);
        user.put("email",email);
        user.put("avata",avata);
        firestore.collection("users").document(currentUser.getUid()).set(user);
    }
}
