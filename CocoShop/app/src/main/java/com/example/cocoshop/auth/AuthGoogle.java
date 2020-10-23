package com.example.cocoshop.auth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class AuthGoogle {
    private static GoogleSignInOptions gso;
    private static FirebaseAuth mAuth;
    private static GoogleSignInClient signInClient;
    private Context context;
    static{
    }
    public AuthGoogle(FirebaseAuth mAuth,Context context,String idToken){
        this.mAuth = mAuth;
        this.context = context;
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(idToken).requestEmail().build();
    }
    public Intent getSignInIntent(){
        signInClient = GoogleSignIn.getClient(context,gso);
        Intent signInGoogle = signInClient.getSignInIntent();
        return signInGoogle;
    }

    public void updatePassword(final String newPassword,String oldPassword){
        final FirebaseUser user = mAuth.getCurrentUser();
        AuthCredential authCredential = GoogleAuthProvider.getCredential(user.getEmail(),"oldPassword");
        user.reauthenticate(authCredential).addOnCompleteListener((Activity) this.context, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    user.updatePassword(newPassword);
                }else{
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void signOut(){
        signInClient.signOut();
    }
}
