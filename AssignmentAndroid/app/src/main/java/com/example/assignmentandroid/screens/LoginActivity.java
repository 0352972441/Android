package com.example.assignmentandroid.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignmentandroid.R;
import com.example.assignmentandroid.screens.courseactivity.CourseRegistrationActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.io.ByteArrayInputStream;

public class LoginActivity extends AppCompatActivity {
    private LoginButton loginButton;
    private TextView uid;
    private CallbackManager mCallbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        uid = (TextView)findViewById(R.id.uid);
        mCallbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                uid.setText(loginResult.getAccessToken().getUserId());
                Log.d("Sucessfully",""+loginResult.getAccessToken().getToken());
                Intent intent = new Intent(LoginActivity.this, CourseRegistrationActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {
                Log.d("Cancel","Cancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("Error",""+error);
            }
        });
    }
}
