package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ViewModel.MessageViewModel;
import com.example.myapplication.screens.Message;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void sendIntent(View view){
        EditText editText = (EditText)findViewById(R.id.text);
        EditText editText1 = (EditText)findViewById(R.id.text1);
        String message = editText.getText().toString();
        String message1 = editText1.getText().toString();
        EditText editText2 = (EditText)findViewById(R.id.text);
        EditText editText3 = (EditText)findViewById(R.id.text1);
        String message2 = editText2.getText().toString();
        String message3 = editText3.getText().toString();
        MessageViewModel ms = new MessageViewModel(message,message1,message2,message3);

        Intent intent = new Intent(this,Message.class);
        intent.putExtra(Message.EXTRA,(int)0);
        startActivity(intent);
    }
}
