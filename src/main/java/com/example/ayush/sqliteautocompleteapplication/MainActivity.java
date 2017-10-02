package com.example.ayush.sqliteautocompleteapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String[] companyNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabase database = new myDatabase(this);
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autoComplete);
        //values that are to be inserted into the database
        companyNames = new String[]{"Apple INC", "Samsung Electronics", "Amazon.com", "Microsoft", "Hitachi", "IBM", "Sony", "Huawei", "Panasonic", "Hewlett Packard Enterprises"};
        ArrayList<String> list = new ArrayList<>();

        Cursor cursor = database.showProduct();
        //if statement prevents values from repeatedly getting inserted into the database
        if(cursor.getCount() == 0){
            for (int i = 0; i < companyNames.length; i++) {
                database.insert_Data(companyNames[i]);
            }
            cursor = database.showProduct();
        }


        while (cursor.moveToNext()) {
            //adds values from the database to the arrayList list
            list.add(cursor.getString(cursor.getColumnIndex("name")));
        }
        //adapter is attached to the autoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,list);
        textView.setAdapter(adapter);

    }
}
