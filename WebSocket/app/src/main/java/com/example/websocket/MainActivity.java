package com.example.websocket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;

public class MainActivity extends AppCompatActivity {
    private ServerSocket serverSocket;
    private static final int PORT = 5001;
    private EditText ed_message;
    private Button btn_Send;
    String message = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed_message = findViewById(R.id.ed_message);
        btn_Send = findViewById(R.id.btn_send);
        try {
            serverSocket = new ServerSocket(PORT,100);
            while (true){
                try {
                    Socket socket = serverSocket.accept();
                    Log.d("Đã kết nối", "Connecting");
                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    InputStreamReader outputStream = new InputStreamReader(socket.getInputStream());
                    BufferedReader reader = new BufferedReader(outputStream);
                    message = reader.readLine();
                    Log.d("Message:",message);
                    ed_message.setText(message);
                    dataOutputStream.writeBytes("Server:"+message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //new Accept().run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Accept implements Runnable {
        @Override
        public void run() {

        }
    }
}
