package com.example.lab5.Screen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab5.R;


public class LessonThreeActivity extends AppCompatActivity {
    EditText mUserName, mPassword;
    LayoutInflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_three);
        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.login, null);
        mUserName = (EditText) view.findViewById(R.id.txtuser);
        mPassword = (EditText)view.findViewById(R.id.txtPassword);
        Toast.makeText(this, "Runnning", Toast.LENGTH_SHORT).show();
    }

    public void ShowLoginAlertDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(LessonThreeActivity.this,mPassword.getText().toString() , Toast.LENGTH_SHORT).show();

                if(mUserName.getText().toString().equals("admin") && mPassword.getText().toString().equals("admin")) {
                    Toast.makeText(LessonThreeActivity.this, "Login Successed", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LessonThreeActivity.this, "UserName or Password Incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setView(R.layout.login);
        builder.show();
    }
}
