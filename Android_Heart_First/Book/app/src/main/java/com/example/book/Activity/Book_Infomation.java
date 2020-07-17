package com.example.book.Activity;
import com.example.book.AsyscTask.TaskFavorite;
import com.example.book.Data.Book;
import com.example.book.Database.BookDatabase;
import com.example.book.R;
import com.example.book.ReaderFile.Reader;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class Book_Infomation extends AppCompatActivity implements View.OnClickListener{
    private String table;
    public static final String EXTRA ="key";
    public static final String EXTRA_TABLE ="hot";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__infomation);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        showInfo();
    }

    public void showInfo(){
        int id = (int) getIntent().getExtras().get(EXTRA);
        table = (String)getIntent().getExtras().getString(EXTRA_TABLE);
        ImageView image = (ImageView)findViewById(R.id.image_info);
        TextView title = (TextView)findViewById(R.id.title_info);
        TextView content = (TextView)findViewById(R.id.context_info);
        Button buy = (Button)findViewById(R.id.buy_info);
        buy.setOnClickListener(this);
        Button read = (Button)findViewById(R.id.read_info);
        read.setOnClickListener(this);
        CheckBox favorite = (CheckBox)findViewById(R.id.abc);
        SQLiteOpenHelper openHelper = new BookDatabase(this);
        try {
            SQLiteDatabase database = openHelper.getReadableDatabase();
            Cursor cursor = database.query(table,new String[]{"TITLE","IMAGE","INFO","CONTENT","FAVORITE"},"_ID= ?",new String[]{Integer.toString(id+1)}
                                                ,null,null,null);
            if(cursor.moveToFirst()){
                int titleData = cursor.getInt(0);
                int imageData = cursor.getInt(1);
                int infoData = cursor.getInt(2);
                int contentData = cursor.getInt(3);
                boolean favoriteData = (cursor.getInt(4) == 1);
                image.setImageResource(imageData);
                title.setText(titleData);
                Reader.reader(content,infoData);
                favorite.setChecked(favoriteData);
            }

            cursor.close();
            database.close();
        }catch (SQLiteException ex){
            Toast.makeText(this,"Database unvaidable",Toast.LENGTH_LONG).show();
        }
    }



    public void actionFavorite(View view){
        int id = (int) getIntent().getExtras().get(EXTRA);
        new TaskFavorite(view, this,table).execute(id);
    }

    @Override
    public void onClick(View v) {
        try {
            int id = (int) getIntent().getExtras().get(EXTRA);
            Intent intent;
            switch (v.getId()) {
                case R.id.buy_info:
                    intent = new Intent(this, Info_Story.class);
                    break;
                case R.id.read_info:
                    intent = new Intent(this, Info_Story.class);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + v.getId());
            }
            intent.putExtra(Info_Story.EXTRA, id);
            intent.putExtra(Info_Story.TABLE, table);
            startActivity(intent);
        }catch (Exception ex){
            Toast.makeText(this, "can not intent", Toast.LENGTH_LONG).show();
        }
    }
}
