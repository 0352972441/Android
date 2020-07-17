package com.example.book.ActivityFragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.book.Activity.Info_Story;
import com.example.book.AdapterRecycel.AdapterRecycler;
import com.example.book.Data.Book;
import com.example.book.Data.DataInterface;
import com.example.book.Database.BookDatabase;
import com.example.book.ListenerFragment.Listener;
import com.example.book.R;

import java.util.ArrayList;


public class HotFragment extends Fragment{
    private  ArrayList<Book> listBookData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        listBookData = new ArrayList<>();
        createAdapterCarView(view);
        return view;
    }

    public void createAdapterCarView(View view){
        SQLiteOpenHelper openHelper = new BookDatabase(view.getContext());
        try{
            SQLiteDatabase database =  openHelper.getReadableDatabase();
            Cursor cursor = database.query("BOOKHOT",new String[]{"TITLE","IMAGE"},null,null,null,null,null);
            while (cursor.moveToNext()){
                int title = cursor.getInt(0);
                int image = cursor.getInt(1);
                Book book = new Book(title,image);
                listBookData.add(book);
            }
            Toast.makeText(view .getContext(), Integer.toString(listBookData.size()), Toast.LENGTH_SHORT).show();
            DataInterface.createDataRecycler(view, listBookData,"BOOKHOT");
            cursor.close();
            database.close();
        }catch (SQLException ex){
            Toast.makeText(view.getContext(), "Database invaidable", Toast.LENGTH_LONG).show();
        }
    }
}
