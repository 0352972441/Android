package com.example.starbuzz;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.starbuzz.AsyncTasks.UpdateDrinkTask;
import com.example.starbuzz.Database.StarbuzzDatabaseHelper;

import java.io.File;

public class DrinksActivity extends Activity {
    static final String EXTRA_DRINKS = "drink";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks);
        setUpIntent();
    }

    public void setUpIntent(){
        //Intent intent = getIntent();
        //int i = intent.getIntExtra(EXTRA_DRINKS,1);
        int id = (int) getIntent().getExtras().get(EXTRA_DRINKS);
        TextView textView = (TextView)findViewById(R.id.check);textView.setText(Integer.toString(id));
        SQLiteOpenHelper openHelper = new StarbuzzDatabaseHelper(this);
        try {
            SQLiteDatabase database = openHelper.getReadableDatabase();
            Cursor cursor = database.query("DRINK", new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID","FAVORITE"}, "_id = ?", new String[]{Integer.toString(id)}, null, null, null);
            if(cursor.moveToFirst()){
                String nameData = cursor.getString(0);
                String descriptionData = cursor.getString(1);
                int imageData = cursor.getInt(2);
                boolean isFavorite = (cursor.getInt(3) == 1);
                ImageView image = (ImageView)findViewById(R.id.image_ac_drink);
                TextView name = (TextView)findViewById(R.id.name_ac_drink);
                TextView description = (TextView)findViewById(R.id.describe_ac_drink);
                CheckBox favorite = (CheckBox)findViewById(R.id.favorite);
                image.setImageResource(imageData);
                name.setText(nameData);
                description.setText(descriptionData);
                image.setContentDescription(nameData);
                favorite.setChecked(isFavorite);
            }
            cursor.close();
            database.close();
        }catch (Exception ex){
            Toast toast = Toast.makeText(this,"Database Unvailable",Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void onCheckFavorite(View view){
        int id = (int)getIntent().getExtras().get(EXTRA_DRINKS);
        new UpdateDrinkTask(this,view).execute(id);
    }
}
