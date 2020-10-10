package com.example.lab5.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.lab5.R;

public class ContentWeb extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_web);
        webView = (WebView)findViewById(R.id.web_view);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String url = (String)getIntent().getExtras().getString("URL");
        webView.loadUrl(url);
    }
}
