package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.Screens.Home_Screen;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private Button mSignin;
    private TextView mSwitch;
    enum modeAuth {LOGIN, SIGNIN};
    private TextInputLayout mComfirmPasswordValidation;
    private EditText mComfirmPassword;
    private modeAuth currentMode = modeAuth.LOGIN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSignin =  (Button)findViewById(R.id.mBtSignin);
        mSwitch = (TextView)findViewById(R.id.mTxtSwitch);
        mComfirmPassword = (EditText)findViewById(R.id.mComfirmPassword);
        mComfirmPasswordValidation = (TextInputLayout)findViewById(R.id.mComfirmPasswordValidation);
        setOnClickTextSwitch();
        setOnClickButtonLogin();
    }

    private void setOnClickButtonLogin(){
        mSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modeAuth.LOGIN == currentMode){
                    Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Home_Screen.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setOnClickTextSwitch(){
        mSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modeAuth.LOGIN == currentMode){
                    mSwitch.setText("I already a account");
                    mSignin.setText("Signup");
                    mComfirmPasswordValidation.setVisibility(View.VISIBLE);
                    mComfirmPassword.setVisibility(View.VISIBLE);
                    currentMode = modeAuth.SIGNIN;
                }else{
                    mSwitch.setText("I don't have account");
                    mSignin.setText("signin");
                    mComfirmPasswordValidation.setVisibility(View.INVISIBLE);
                    mComfirmPassword.setVisibility(View.INVISIBLE);
                    currentMode = modeAuth.LOGIN;
                }
            }
        });
    }

}
