package com.example.book.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.book.R;

public class BookDatabase extends SQLiteOpenHelper {
    private static  final String nameDatabase = "BOOKDATABASE";
    private static  final int version = 4;
    public BookDatabase(Context context) {
        super(context, nameDatabase, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0 , version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private static void insertDataSell(SQLiteDatabase database, int title, int image){
        ContentValues bookValue = new ContentValues();
        bookValue.put("TITLE",title);
        bookValue.put("IMAGE",image);
        database.insert("BOOKSELL", null, bookValue);
    }
    private static void insertDataHot(SQLiteDatabase database, int title, int image){
        ContentValues bookValue = new ContentValues();
        bookValue.put("TITLE",title);
        bookValue.put("IMAGE",image);
        database.insert("BOOKHOT", null, bookValue);
    }

    private void updateMyDatabase(SQLiteDatabase database, int oldVersion, int newVersion){
        if(oldVersion < 1) {
            database.execSQL("CREATE TABLE BOOKSELL(_ID INTEGER PRIMARY KEY AUTOINCREMENT," + "TITLE INTEGER," + "IMAGE INTEGER);");
            insertDataSell(database, R.string.java,R.drawable.java);
            insertDataSell(database,R.string.unexpexted,R.drawable.unexpected);
            insertDataSell(database,R.string.MBA,R.drawable.mba);
            insertDataSell(database,R.string.minicraft,R.drawable.minicraf);
            insertDataSell(database,R.string.getguy,R.drawable.get_gui);
            insertDataSell(database,R.string.question,R.drawable.question);
            insertDataSell(database,R.string.walking,R.drawable.walking);
            insertDataSell(database,R.string.enemy,R.drawable.enymi);
            insertDataSell(database,R.string.zero,R.drawable.zero);
        }
        if(oldVersion <2){
            database.execSQL("CREATE TABLE BOOKHOT(_ID INTEGER PRIMARY KEY AUTOINCREMENT," + "TITLE INTEGER," + "IMAGE INTEGER);");
            insertDataHot(database, R.string.titan,R.drawable.titan);
            insertDataHot(database,R.string.tracy,R.drawable.tracy);
            insertDataHot(database,R.string.theory,R.drawable.gametheory);
            insertDataHot(database,R.string.minicraft,R.drawable.minicraf);
            insertDataHot(database,R.string.alldoom,R.drawable.alldoom);
            insertDataHot(database,R.string.spirit,R.drawable.yuri);
            insertDataHot(database,R.string.scherlock,R.drawable.sherlockholmesjpg);
            insertDataHot(database,R.string.sonic,R.drawable.sonic);
            insertDataHot(database,R.string.soul,R.drawable.sour);
            insertDataHot(database,R.string.abraham,R.drawable.abraham);
            insertDataHot(database,R.string.sauquay,R.drawable.sauquay);
        }
        if(oldVersion <3){
            database.execSQL("ALTER TABLE BOOKHOT ADD COLUMN CONTENT INTEGER");
            database.execSQL("ALTER TABLE BOOKSELL ADD COLUMN CONTENT INTEGER");
            database.execSQL("ALTER TABLE BOOKHOT ADD COLUMN INFO INTEGER");
            database.execSQL("ALTER TABLE BOOKSELL ADD COLUMN INFO INTEGER");
            insertColumn(database,"BOOKHOT","INFO",R.drawable.abraham,R.raw.abraham);
            insertColumn(database,"BOOKHOT","INFO",R.drawable.sour,R.raw.soul);
            insertColumn(database,"BOOKHOT","INFO",R.drawable.titan,R.raw.get_the_guy);
            insertColumn(database,"BOOKHOT","INFO",R.drawable.tracy,R.raw.alldoom);
            insertColumn(database,"BOOKHOT","INFO",R.drawable.gametheory,R.raw.get_the_guy);
            insertColumn(database,"BOOKHOT","INFO",R.drawable.minicraf,R.raw.abraham);
            insertColumn(database,"BOOKHOT","INFO",R.drawable.alldoom,R.raw.alldoom);
            insertColumn(database,"BOOKHOT","INFO",R.drawable.sherlockholmesjpg,R.raw.abraham);
            insertColumn(database,"BOOKHOT","INFO",R.drawable.sonic,R.raw.sonic);
            insertColumn(database,"BOOKHOT","INFO",R.drawable.sauquay,R.raw.sonic);
            insertColumn(database,"BOOKHOT","INFO",R.drawable.yuri,R.raw.soul);
            insertColumn(database,"BOOKHOT","CONTENT",R.drawable.abraham,R.raw.abraham_content);

            insertColumn(database,"BOOKSELL","INFO",R.drawable.java,R.raw.soul);
            insertColumn(database,"BOOKSELL","INFO",R.drawable.unexpected,R.raw.question);
            insertColumn(database,"BOOKSELL","INFO",R.drawable.mba,R.raw.abraham);
            insertColumn(database,"BOOKSELL","INFO",R.drawable.minicraf,R.raw.alldoom);
            insertColumn(database,"BOOKSELL","INFO",R.drawable.question,R.raw.question);
            insertColumn(database,"BOOKSELL","INFO",R.drawable.walking,R.raw.alldoom);
            insertColumn(database,"BOOKSELL","INFO",R.drawable.enymi,R.raw.get_the_guy);
            insertColumn(database,"BOOKSELL","INFO",R.drawable.zero,R.raw.alldoom);
            insertColumn(database,"BOOKSELL","INFO",R.drawable.get_gui,R.raw.get_the_guy);

        }
        if(oldVersion <4){
            database.execSQL("ALTER TABLE BOOKHOT ADD COLUMN FAVORITE NUMERIC");
            database.execSQL("ALTER TABLE BOOKSELL ADD COLUMN FAVORITE NUMERIC");
        }
    }

    public void insertColumn(SQLiteDatabase database, String nameData, String nameTable, int postion,int value){
        ContentValues contentValues = new ContentValues();
        contentValues.put(nameTable,value);
        database.update(nameData,contentValues,"IMAGE=?",new String[]{Integer.toString(postion)});
    }

}
