package com.example.book.Activity;
import com.example.book.Database.BookDatabase;
import com.example.book.R;
import com.example.book.ReaderFile.Reader;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Info_Story extends AppCompatActivity {
    public static  final String EXTRA ="key";
    public static  final String TABLE ="table";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info__story);
        int id = (int)getIntent().getExtras().get(EXTRA);
        String table = (String)getIntent().getExtras().get(TABLE);
        SQLiteOpenHelper openHelper = new BookDatabase(this);
        try{
            SQLiteDatabase database = openHelper.getReadableDatabase();
            Cursor cursor = database.query(table,new String[]{"CONTENT"},"_ID = ?", new String[]{Integer.toString(id+1)},null,null,null,null);
            if(cursor.moveToFirst()){
                int file = cursor.getInt(0);
                TextView textView = (TextView)findViewById(R.id.content_story);
                Reader.reader(textView,file);
            }
            cursor.close();
            database.close();
        }catch (SQLiteException ex){
            Toast.makeText(this, "Database unvaidable", Toast.LENGTH_SHORT).show();
        }
    }
}
