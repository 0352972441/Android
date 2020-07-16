package com.example.overloading;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;

public class AddressDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_detail);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        list();
    }

    public void list(){
        String[] title = new String[CurrentAddress.currentAddresses.length];
        for(int i=0; i<title.length; i++){
            title[i] = CurrentAddress.currentAddresses[i].getAddress();
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,title);
        ListView listView = (ListView)findViewById(R.id.listaddress);
        listView.setAdapter(arrayAdapter);
        titleIntent titleIntent = new titleIntent();
        listView.setOnItemClickListener(titleIntent);

    }

    class titleIntent implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(AddressDetail.this,Detail.class);
            intent.putExtra(Detail.EXTRA,(int)id);
            startActivity(intent);
        }
    }

}
