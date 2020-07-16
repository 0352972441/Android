package com.example.starbuzz;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.AdapterView;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.starbuzz.Database.StarbuzzDatabaseHelper;

public class MainActivity extends Activity {
    SQLiteDatabase database;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if((int)id ==0){
                    Intent intent = new Intent(MainActivity.this,DrinkCategoryActivity.class);
                    startActivity(intent);
                }
            }
        };
        ListView listView = (ListView) findViewById(R.id.option);
        listView.setOnItemClickListener(listener);
        addFavorite();
    }

    public void addFavorite(){
        SQLiteOpenHelper openHelper = new StarbuzzDatabaseHelper(this);
        try{
            database = openHelper.getReadableDatabase();
            cursor = database.query("DRINK",new String[]{"_id","NAME"},"FAVORITE = 1",null,null,null,null);
            ListView listFavorite = (ListView)findViewById(R.id.list_favorite);
            SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,cursor,new String[]{"NAME"},new int[]{android.R.id.text1},0);
            listFavorite.setAdapter(cursorAdapter);
            listFavorite.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MainActivity.this,DrinksActivity.class);
                    intent.putExtra(DrinksActivity.EXTRA_DRINKS,(int)id);
                    startActivity(intent);
                }
            });
        }catch (SQLiteException ex){
            Toast.makeText(this, "Database unvailable", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRestart() {
        super.onRestart();
        Cursor newcursor = database.query("DRINK", new String[]{"_id","NAME"}, "FAVORITE = 1", null, null, null, null);
        ListView listFavorite = (ListView) findViewById(R.id.list_favorite);
        CursorAdapter cursorAdapter = (CursorAdapter) listFavorite.getAdapter();
        cursorAdapter.changeCursor(newcursor);
        cursor = newcursor;
}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
        cursor.close();
    }
}
