package com.example.mymessager;

import android.app.Activity;
import  android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;

public class ReceiveMessage extends Activity {
    public static final String EXTRA_MESSAGE = "Message";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_message);
        Intent intent = getIntent();
        TextView text = (TextView) findViewById(R.id.message);
        String content = intent.getStringExtra(intent.EXTRA_TEXT);

        text.setText(content);
    }
}
