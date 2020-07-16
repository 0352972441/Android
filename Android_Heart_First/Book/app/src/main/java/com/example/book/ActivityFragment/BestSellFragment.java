package com.example.book.ActivityFragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.book.Activity.Book_Infomation;
import com.example.book.Activity.Info_Story;
import com.example.book.AdapterRecycel.AdapterRecycler;
import com.example.book.Data.Book;
import com.example.book.Data.DataInterface;
import com.example.book.Database.BookDatabase;
import com.example.book.ListenerFragment.Listener;
import com.example.book.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BestSellFragment extends Fragment implements Listener {
    private ArrayList<Book> listDataBook = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_best_sell, container, false);
        createRecycleView(view);
        return view;
    }

    private void createRecycleView(View view){
        SQLiteOpenHelper openHelper = new BookDatabase(view.getContext());
        try {
            SQLiteDatabase database = openHelper.getReadableDatabase();
            Cursor cursor = database.query("BOOKSELL",new String[]{"TITLE","IMAGE"},null,null,null,null,null);
            while (cursor.moveToNext()){
                int title = cursor.getInt(0);
                int image = cursor.getInt(1);
                Book book = new Book(title,image);
                listDataBook.add(book);
            }
            Toast.makeText(view.getContext(),Integer.toString(listDataBook.size()),Toast.LENGTH_LONG).show();
            DataInterface.createDataRecycler(view, listDataBook,"BOOKSELL");
            cursor.close();
            database.close();
        }catch (SQLiteException ex){
            Toast.makeText(view.getContext(),"Database unvailable",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(getActivity(), Book_Infomation.class);
        intent.putExtra(Book_Infomation.EXTRA,position);
        getActivity().startActivity(intent);
    }
}
