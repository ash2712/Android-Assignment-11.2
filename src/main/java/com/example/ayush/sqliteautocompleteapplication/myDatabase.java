package com.example.ayush.sqliteautocompleteapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ayush on 10/2/2017.
 */

public class myDatabase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactsManager";
    private static final String TABLE_COMPANIES = "companies";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";


    public myDatabase(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Table is created
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_COMPANIES + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insert_Data(String name)
    {
        //to write data into the table
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,name);
        long res=db.insert(TABLE_COMPANIES,null,values);
        if(res==-1)
        {
            return  false;
            //if data is not inserted return false
        }
        else
        {
            return  true;
            //else return true
        }
    }

    public Cursor showProduct() {
        //fetching data
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from companies", null);
        if (cursor != null)
            return cursor;
        else
            return null;
    }

}
