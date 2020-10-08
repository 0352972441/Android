package com.example.assignmentandroid.screens.newsactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.assignmentandroid.R;

public class NewsContextActivity extends AppCompatActivity {
    private WebView webView;
    public static final String LINK = "link";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_context);
        webView = (WebView)findViewById(R.id.web_view);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String link = getIntent().getExtras().getString(LINK);
        webView.loadUrl(link);
    }
}
