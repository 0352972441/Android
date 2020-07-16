package com.example.book.ReaderFile;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Reader {
    public static void reader(TextView content, int fileData) {
        InputStream file = content.getResources().openRawResource(fileData);
        InputStreamReader stream = new InputStreamReader(file);
        BufferedReader reader = new BufferedReader(stream);
        String data = "";
        StringBuffer buffer = new StringBuffer();
        try {
            while ((data = reader.readLine()) != null){
                buffer.append(data+"\n");
            }
            content.setText(buffer);
            reader.close();
        }catch (IOException e){
            Toast.makeText(content.getContext(), "Reader file fail", Toast.LENGTH_SHORT).show();
        }

    }
}
