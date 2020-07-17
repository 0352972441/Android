package com.example.book.ActivityFragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.book.Data.Book;
import com.example.book.Data.DataInterface;
import com.example.book.Database.BookDatabase;
import com.example.book.R;

import java.sql.SQLData;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Favorite_Fragment extends Fragment {
    private ArrayList<Book> bookArrayList = new ArrayList<>();
    public Favorite_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_, container, false);
        createAdapterCarView(view);
        return view;
    }

    public void createAdapterCarView(View view){
        SQLiteOpenHelper openHelper = new BookDatabase(view.getContext());
        try{
            SQLiteDatabase database = openHelper.getWritableDatabase();
            Cursor cursor = database.query("BOOKHOT",new String[]{"TITLE, IMAGE"},"FAVORITE = 1",null,null,null,null);
            while(cursor.moveToNext()){
                int title = cursor.getInt(0);
                int image = cursor.getInt(1);
                Book book = new Book(title, image);
                bookArrayList.add(book);
            }
            DataInterface.createDataRecycler(view,bookArrayList,"BOOKHOT");
            cursor.close();
            database.close();
        }catch (SQLiteException ex){
            Toast.makeText(view.getContext(), "Database unvailable", Toast.LENGTH_SHORT).show();
        }
    }


}
