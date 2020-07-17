package com.example.overloading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

public class Address extends AppCompatActivity {
    private ShareActionProvider shareActionProvider;
    private String name = "Hello";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        onClickItemSeclected onClickItemSeclected = new onClickItemSeclected();
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(onClickItemSeclected);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.share,menu);
        MenuItem menuItem = menu.findItem(R.id.share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setProviderActionInten();
        return  super.onCreateOptionsMenu(menu);
    }

    public void setProviderActionInten(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,name);
        shareActionProvider.setShareIntent(intent);
    }

    class onClickItemSeclected implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = null;
            if(position ==0){
               intent = new Intent(Address.this,AddressDetail.class);
               startActivity(intent);
            }else if(position ==1){
                intent = new Intent(Address.this,AddressDetail.class);
                startActivity(intent);
            }
        }
    }


}
