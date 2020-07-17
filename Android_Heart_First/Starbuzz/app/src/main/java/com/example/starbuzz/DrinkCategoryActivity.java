package com.example.starbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.starbuzz.Database.StarbuzzDatabaseHelper;

public class DrinkCategoryActivity extends AppCompatActivity {
    SQLiteDatabase database;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_category);
        //ArrayAdapter<Drink> drinkArrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,Drink.drinks);
        retriveData();

    }

    public void retriveData(){
        SQLiteOpenHelper openHelper = new StarbuzzDatabaseHelper(this);
        try {
            database = openHelper.getReadableDatabase();
            cursor = database.query("DRINK",new String[]{"_id","NAME"},null,null,null,null,null);
            SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,cursor,new String[]{"NAME"},new int[]{android.R.id.text1},0);
            ListView listView = (ListView) findViewById(R.id.list_drink);
            listView.setAdapter(cursorAdapter);
            IntentDrink intentDrink = new IntentDrink();
            listView.setOnItemClickListener(intentDrink);
        }catch (SQLiteException ex){
            Toast.makeText( this, "Database unvailable", Toast.LENGTH_SHORT).show();
        }
    }

    class IntentDrink implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(DrinkCategoryActivity.this, DrinksActivity.class);
            intent.putExtra(DrinksActivity.EXTRA_DRINKS,(int)id);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        database.close();
    }


}
