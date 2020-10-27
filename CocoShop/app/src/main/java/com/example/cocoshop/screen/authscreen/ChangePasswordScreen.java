package com.example.cocoshop.screen.authscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.CircularDotsLoader;
import com.example.cocoshop.models.User;
import com.example.cocoshop.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordScreen extends AppCompatActivity {
    private TextInputEditText edOldPassword,edNewPassword,edConfirmPassword;
    private Button btnChangePassword;
    private String email;
    private String newPassword;
    private String confirmPassword;
    private CircularDotsLoader mCircularDotsLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_screen);
        edOldPassword = (TextInputEditText)findViewById(R.id.edoldpassword);
        edNewPassword = (TextInputEditText)findViewById(R.id.ednewpassword);
        edConfirmPassword = (TextInputEditText)findViewById(R.id.edconfirmpassword);
        btnChangePassword = (Button)findViewById(R.id.btnChangePassword);
        mCircularDotsLoader = findViewById(R.id.progress_circular);
        onClickChangePassword();
    }

    private void onClickChangePassword(){
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCircularDotsLoader.setVisibility(View.VISIBLE);
                if(checkPassword()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    user.updatePassword(newPassword).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            mCircularDotsLoader.setVisibility(View.INVISIBLE);
                            Snackbar.make(mCircularDotsLoader,e.getMessage().toString(),Snackbar.LENGTH_LONG).show();
                        }
                    });
                    Toast.makeText(ChangePasswordScreen.this, "Change Password successed", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    mCircularDotsLoader.setVisibility(View.INVISIBLE);
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
        }else if(newPassword.length() < 6){
            edNewPassword.setError("Password  at least 6 characters");
            return false;
        }else if(!confirmPassword.equals(newPassword)){
            edConfirmPassword.setError("Password does not match");
            return false;
        }
        return  true;
    }

}
