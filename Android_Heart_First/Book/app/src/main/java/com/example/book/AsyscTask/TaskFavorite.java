package com.example.book.AsyscTask;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.book.Database.BookDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.book.R;
public class TaskFavorite extends AsyncTask<Integer,Void,Boolean>{
    View view;
    Context context;
    private  String table;
    ContentValues favorite;
    public TaskFavorite(View view, Context context,String table) {
        this.view = view;
        this.context = context;
        this.table = table;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        CheckBox button = (CheckBox) view.findViewById(R.id.abc);
        favorite = new ContentValues();
        favorite.put("FAVORITE",button.isChecked());
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        if(!result){
            Toast.makeText(context,"Database unvailable",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected Boolean doInBackground(Integer... integers) {
        int id = integers[0];
        SQLiteOpenHelper openHelper = new BookDatabase(context);
        try{
            SQLiteDatabase database = openHelper.getWritableDatabase();
            database.update(table,favorite,"_ID = ?",new String[]{Integer.toString(id+1)});
            database.close();
            return true;
        }catch(SQLiteException ex){
            return false;
        }
    }
}
