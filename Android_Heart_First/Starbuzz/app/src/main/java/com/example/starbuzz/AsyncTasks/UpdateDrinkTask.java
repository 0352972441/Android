package com.example.starbuzz.AsyncTasks;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.text.PrecomputedText;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.core.text.PrecomputedTextCompat;

import com.example.starbuzz.Database.StarbuzzDatabaseHelper;
import com.example.starbuzz.DrinksActivity;
import com.example.starbuzz.R;

import javax.xml.transform.Result;

public class UpdateDrinkTask extends AsyncTask<Integer, Void , Boolean> {
    private ContentValues drinkvalue;
    private Context context;
    private View view;


    public UpdateDrinkTask(Context context, View view) {
        this.context = context;
        this.view = view;
    }

    public void onPreExecute(){
        drinkvalue = new ContentValues();
        CheckBox checkBox = (CheckBox)view.findViewById(R.id.favorite);
        drinkvalue.put("FAVORITE",checkBox.isChecked());
    }

    @Override
    protected Boolean doInBackground(Integer... integers) {
        int drinkid = integers[0];
        SQLiteOpenHelper openHelper = new StarbuzzDatabaseHelper(context);
        try{
            SQLiteDatabase database = openHelper.getWritableDatabase();
            database.update("DRINK",drinkvalue,"_id = ?",new String[]{Integer.toString(drinkid)});
            database.close();
            return true;
        }catch(SQLiteException ex){
            return false;
        }

    }

    public void onPostExecute(Boolean result){
        if(!result){
            Toast.makeText(context,"Database unvailable",Toast.LENGTH_LONG).show();
        }
    }
}
