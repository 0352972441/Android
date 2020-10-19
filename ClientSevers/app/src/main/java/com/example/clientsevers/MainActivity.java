package com.example.clientsevers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private Socket socket;
    private EditText ed_message;
    private TextView ed_port;
    private Button bntSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed_message =(EditText) findViewById(R.id.ed_message);
        bntSend = (Button)findViewById(R.id.btn_send);
        bntSend.setText("Send");
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    try {
                        socket = new Socket("127.0.0.1",5001);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    bntSend.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(socket!= null){
                                DataOutputStream dataOutputStream = null;
                                try {
                                    dataOutputStream = new DataOutputStream(socket.getOutputStream());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    dataOutputStream.writeBytes("Hello");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
            });
    }
}
