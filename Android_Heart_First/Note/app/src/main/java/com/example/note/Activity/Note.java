package com.example.note.Activity;
import com.example.note.Data.CustomGridView;
import com.example.note.R;
import com.example.note.TransactionFragment.TranFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class Note extends AppCompatActivity {
    private ArrayList<com.example.note.Data.Note> title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        addGridAdapter();
    }

    public void addGridAdapter(){
        final GridView gridView = findViewById(R.id.gridview);
        CustomGridView customGridView = new CustomGridView(getListData(),this);
        gridView.setAdapter(customGridView);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Intent intent = new Intent(Note.this, AddNote.class);
                startActivity(intent);*/
                Toast.makeText(Note.this,getListData().get(position).getTitle(),Toast.LENGTH_LONG).show();
            }
        });

    }
    private ArrayList<com.example.note.Data.Note> getListData(){

        ArrayList<com.example.note.Data.Note> listData = new ArrayList<>();
        com.example.note.Data.Note google = new com.example.note.Data.Note("Google",R.mipmap.snow);
        com.example.note.Data.Note yahoo = new com.example.note.Data.Note("Yahoo",R.mipmap.sun);
        com.example.note.Data.Note gmail = new com.example.note.Data.Note("Gmail",R.mipmap.snow);
        com.example.note.Data.Note bing = new com.example.note.Data.Note("Bing",R.mipmap.sun);
        listData.add(google);
        listData.add(yahoo);
        listData.add(gmail);
        listData.add(bing);
        return listData;
    }




  /*  public void addWebside(){
        final GridView gridView = findViewById(R.id.gridview);
        com.example.note.Data.Note google = new com.example.note.Data.Note("Google","Google.com");
        com.example.note.Data.Note yahoo = new com.example.note.Data.Note("Yahoo","yahoo.com");
        com.example.note.Data.Note gmail = new com.example.note.Data.Note("Gmail","gmail.com");
        com.example.note.Data.Note bing = new com.example.note.Data.Note("Bing","Bing.com");
        com.example.note.Data.Note lazada = new com.example.note.Data.Note("Lazada","lazada.com");
        title = new ArrayList<>();
        title.add(google);
        title.add(yahoo);
        title.add(gmail);
        title.add(bing);
        title.add(lazada);
        ArrayAdapter<com.example.note.Data.Note> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, title);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                *//*Object o = gridView.getItemAtPosition(position);
                com.example.note.Data.Note note = (com.example.note.Data.Note)o;*//*
                Toast.makeText(Note.this,title.get(position).getUrl(),Toast.LENGTH_LONG).show();
            }
        });}*/

}
