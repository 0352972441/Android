package com.example.mymessager;

import android.app.Activity;
import android.view.View;
import android.os.Bundle;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.EditText;
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickMessage(View view){
        //Intent intent = new Intent(this, ReceiveMessage.class);
        EditText message = (EditText) findViewById(R.id.message);
        String messageText = message.getText().toString();
        //intent.putExtra(ReceiveMessage.EXTRA_MESSAGE,messageText);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("Text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,messageText);
        String title = getString(R.string.Choose);
        Intent chooseIntent = Intent.createChooser(intent,title);
        startActivity(chooseIntent);
    }
}
