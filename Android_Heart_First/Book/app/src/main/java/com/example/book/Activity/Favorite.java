package com.example.book.Activity;
import com.example.book.AdapterRecycel.AdapterRecycler;
import com.example.book.Data.Book;
import com.example.book.Data.DataInterface;
import com.example.book.Database.BookDatabase;
import com.example.book.ListenerFragment.Listener;
import com.example.book.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Favorite extends AppCompatActivity {
    private ArrayList<String> table;
    private SQLiteDatabase database;
    private Cursor cursor;
    public static final  String EXTRA_FAVORITESELL = "sell";
    public static final  String EXTRA_FAVORITEHOT = "hot";
    private ArrayList<Book> bookArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        addFavorite();
    }

    protected void addFavorite() {
        super.onRestart();
        table = new ArrayList<>();
        String booksell = getIntent().getExtras().getString(EXTRA_FAVORITESELL);
        String bookhot = getIntent().getExtras().getString(EXTRA_FAVORITEHOT);
        table.add(bookhot);
        table.add(booksell);
        SQLiteOpenHelper openHelper = new BookDatabase(this);
        try{
            database = openHelper.getWritableDatabase();
            for(int i=0 ; i< table.size(); i++){
                cursor = database.query(table.get(i),new String[]{"TITLE, IMAGE"},"FAVORITE = 1",null,null,null,null);
                while(cursor.moveToNext()){
                    int title = cursor.getInt(0);
                    int image = cursor.getInt(1);
                    Book book = new Book(title, image);
                    bookArrayList.add(book);
                }
            }
            AdapterRecycler adapterRecycler = new AdapterRecycler(bookArrayList);
            RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_layout);
            recyclerView.setAdapter(adapterRecycler);
            GridLayoutManager manager = new GridLayoutManager(this, 3);
            recyclerView.setLayoutManager(manager);
        }catch (SQLiteException ex){
            Toast.makeText(this, "Database unvailable", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();Cursor newcursor = null;
        for(int i=0 ; i< table.size(); i++){
            newcursor = database.query(table.get(i),new String[]{"TITLE, IMAGE"},"FAVORITE = 1",null,null,null,null);
            while(cursor.moveToNext()){
                int title = newcursor.getInt(0);
                int image = newcursor.getInt(1);
                Book book = new Book(title, image);
                bookArrayList.add(book);
            }
        }
            AdapterRecycler adapterRecycler = new AdapterRecycler(bookArrayList);
            RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_layout);
            recyclerView.setAdapter(adapterRecycler);
            GridLayoutManager manager = new GridLayoutManager(this, 3);
            recyclerView.setLayoutManager(manager);
            cursor = newcursor;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        database.close();
    }
}
