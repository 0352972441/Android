package com.example.cocoshop.screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cocoshop.R;
import com.example.cocoshop.adapter.audioadapter.CardItemAudioPopularAdapter;
import com.example.cocoshop.dao.AudioDao;
import com.example.cocoshop.dao.SearchResultDao;
import com.example.cocoshop.models.audiomodels.Audio;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private EditText edSeach;
    private RecyclerView searchResultRecycler;
    private ImageView img_search;
    private String searching = "";
    private SearchResultDao searchResultDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        edSeach = findViewById(R.id.ed_search);
        searchResultRecycler = findViewById(R.id.search_results_recycler);
        img_search = findViewById(R.id.img_search);

        searching = getIntent().getExtras().getString("search");
        edSeach.setText(searching);
        searchResultDao = new SearchResultDao(searchResultRecycler);
        searchResultDao.execute(searching);
        onClickFind();
    }

    private void onClickFind(){
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edSeach.getText().toString().isEmpty()){
                    return;
                }else{
                    searchResultDao.search(edSeach.getText().toString());
                }
            }
        });
    }
}
