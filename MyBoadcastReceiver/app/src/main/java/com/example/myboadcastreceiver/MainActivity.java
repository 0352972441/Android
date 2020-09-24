package com.example.myboadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myboadcastreceiver.Broadcast.BroadcastReveicer;
import com.example.myboadcastreceiver.Broadcast.SendBroadcast;
import com.example.myboadcastreceiver.Screen.Lesson2;

public class MainActivity extends AppCompatActivity {
    SendBroadcast sendBoarcast;
    BroadcastReveicer phoneNumberBoadcast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendBoarcast = new SendBroadcast();
        IntentFilter intentFilter = new IntentFilter("test.custombroadcast");
        registerReceiver(sendBoarcast,intentFilter);
    }

    public void onClickButton(View view){
        Button bntClick = (Button)view;
        Intent intent;
        switch (view.getId()){
            case R.id.btnLs1:
                break;
            case R.id.btnLs2:
                intent = new Intent();
                intent.putExtra("name","Toán");
                intent.setAction("test.custombroadcast");
                sendBroadcast(intent);
                break;
            case R.id.btnLs3:
                intent = new Intent(this, Lesson2.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(sendBoarcast);
    }
}
