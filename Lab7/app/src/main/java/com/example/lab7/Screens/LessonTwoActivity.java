package com.example.lab7.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab7.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class LessonTwoActivity extends AppCompatActivity {
    EditText mUserName, mEmail, mPassword;
    private TextInputLayout mValidationUser, mValidationEmail, mValidationPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_two);
        mValidationUser = (TextInputLayout)findViewById(R.id.validation_userName);
        mValidationEmail = (TextInputLayout)findViewById(R.id.validation_email);
        mValidationPassword = (TextInputLayout)findViewById(R.id.validation_password);
        Button mlogin = (Button)findViewById(R.id.buttonLogin);
        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validationUserName() || !validationEmail() || !validationPassword()){
                    return;
                }else{
                    String input = mValidationUser.getEditText().getText().toString().trim();
                    input+= "\n";
                    input += mValidationEmail.getEditText().getText().toString().trim();
                    input+="\n";
                    input += mValidationPassword.getEditText().getText().toString().trim();
                    Toast.makeText(LessonTwoActivity.this, input, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validationUserName(){
        String userName = mValidationUser.getEditText().getText().toString().trim();
        if(userName.isEmpty()){
            mValidationUser.setError("User can't empty");
            return false;
        }else if(userName.length() < 6){
            mValidationUser.setError("User name least 6 character");
            return false;
        }else {
            mValidationUser.setError(null);
            return true;
        }
    }

    private boolean validationEmail(){
        String email = mValidationEmail.getEditText().getText().toString().trim();
        if(email.isEmpty()){
            mValidationEmail.setError("Email can't empty");
            return false;
        }
        if(!email.contains("@")){
            mValidationEmail.setError("Please enter a valid address email ");
            return false;
        }
        mValidationEmail.setError(null);
        return true;
    }

    private boolean validationPassword(){
        String password = mValidationPassword.getEditText().getText().toString().trim();
        if(password.isEmpty()){
            mValidationPassword.setError("Password can't empty");
            return false;
        }
        if(password.length() <6 ){
            mValidationPassword.setError("Please enter password least 6 character");
            return false;
        }
        mValidationPassword.setError(null);
        return true;
    }

}
