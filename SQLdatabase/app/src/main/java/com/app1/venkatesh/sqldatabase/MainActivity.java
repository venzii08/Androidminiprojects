package com.app1.venkatesh.sqldatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase myDataBase = this.openOrCreateDatabase("Events", MODE_PRIVATE,null);

        myDataBase.execSQL("CREATE TABLE IF NOT EXISTS events (eventname VARCHAR, year INT(3) )");

        myDataBase.execSQL("INSERT INTO events (eventname, year) VALUES ('carnival', 2015)");
        myDataBase.execSQL("INSERT INTO events (eventname, year) VALUES ('volborg', 2016)");

        Cursor c = myDataBase.rawQuery("SELECT * FROM events", null);

        int eventsIndex = c.getColumnIndex("eventname");
        int yearIndex = c.getColumnIndex("year");

        c.moveToFirst();

        while (c!= null){
            Log.i("eventname", c.getString(eventsIndex));
            Log.i("year", c.getString(yearIndex));

            c.moveToNext();
        }

    }
}
