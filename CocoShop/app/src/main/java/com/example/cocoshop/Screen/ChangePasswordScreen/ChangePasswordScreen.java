package com.example.cocoshop.Screen.ChangePasswordScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cocoshop.Models.User;
import com.example.cocoshop.R;
import com.example.cocoshop.fireStore.FireStoreUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ChangePasswordScreen extends AppCompatActivity {
    private TextInputEditText edOldPassword,edNewPassword,edConfirmPassword;
    private Button btnChangePassword;
    private String email;
    private String newPassword;
    private String confirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_screen);
        edOldPassword = (TextInputEditText)findViewById(R.id.edoldpassword);
        edNewPassword = (TextInputEditText)findViewById(R.id.ednewpassword);
        edConfirmPassword = (TextInputEditText)findViewById(R.id.edconfirmpassword);
        btnChangePassword = (Button)findViewById(R.id.btnChangePassword);
        onClickChangePassword();
    }

    private void onClickChangePassword(){
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPassword()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    user.updatePassword(newPassword);
                    Toast.makeText(ChangePasswordScreen.this, "Change Password successed", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(ChangePasswordScreen.this, "Change password failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkPassword(){
        email = edOldPassword.getText().toString();
        newPassword = edNewPassword.getText().toString();
        confirmPassword = edConfirmPassword.getText().toString();
        if(!email.equals(User.getEmail())){
            edOldPassword.setError("Incorrect email");
            return false;
        }else if(newPassword.isEmpty()){
            edNewPassword.setError("Password hasn't to empty");
            return false;
        }else if(newPassword.length() <= 6){
            edNewPassword.setError("Password  at least 6 characters");
            return false;
        }else if(!confirmPassword.equals(newPassword)){
            edConfirmPassword.setError("Password does not match");
            return false;
        }
        return  true;
    }

}
