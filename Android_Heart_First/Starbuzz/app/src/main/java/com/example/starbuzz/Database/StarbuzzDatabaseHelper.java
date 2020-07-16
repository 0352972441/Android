package com.example.starbuzz.Database;

import com.example.starbuzz.R;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StarbuzzDatabaseHelper extends SQLiteOpenHelper {
    private static final String NAMEDATABASE = "starbuzz";
    private static final int VERTION = 2;
    public StarbuzzDatabaseHelper(Context context){
        super(context,NAMEDATABASE,null,VERTION);

    };

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db,0,VERTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db,oldVersion,newVersion);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion){
        if(oldVersion <1){
            db.execSQL("Create Table DRINK (_id integer primary key AUTOINCREMENT ,"+"NAME Text,"
                    +"DESCRIPTION TEXT,"+"IMAGE_RESOURCE_ID INTEGER);");
            insertData(db, "Latte", "Espresso and steamed milk", R.drawable.latte);
            insertData(db, "Cappuccino", "Espresso, hot milk and steamed-milk foam",
                    R.drawable.cappuccino);
            insertData(db, "Filter", "Our best drip coffee", R.drawable.filter);
        }
        if(oldVersion <2){
            db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC");
        }
    }

    public static void insertData(SQLiteDatabase database, String name, String description, int image){
        ContentValues drink = new ContentValues();
        drink.put("NAME",name);
        drink.put("DESCRIPTION",description);
        drink.put("IMAGE_RESOURCE_ID",image);
        database.insert("DRINK",null,drink);
       /* drink.put("NAME","new");
        database.update("DRNK",drink,"Name = ?",new String[]{"Lattu"});
        database.delete("DRINK","Name= ?",new String[]{"lattu"});
        database.execSQL("DROP TABLE DRINK");
        database.execSQL("ALTER TABLE DRINKS RENAME TO DRUNKS");*/
    }
}
