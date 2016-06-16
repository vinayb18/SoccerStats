package com.example.student.soccerstats;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by student on 08/06/2016.
 */
public class SoccerDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "soccerstats";
    private static final int DB_VERSION = 1;

    public SoccerDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("CREATE TABLE SOCCERSTATS (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME TEXT, " +
                "TEAM TEXT, " +
                "COUNTRY TEXT, " +
                "POSITION TEXT, " +
                "AGE INTEGER);");
    }

    public void insertElement(SQLiteDatabase db, ContentValues newContent) {

        db.insert("SOCCERSTATS", null, newContent);

    }

    public int alterElement(SQLiteDatabase db, ContentValues alteredContent, String where,
                            String[] whereArgs) {

        return db.update("SOCCERSTATS", alteredContent, where, whereArgs);

    }

    public int deleteElement(SQLiteDatabase db, String where, String[] whereArgs) {

        return db.delete("SOCCERSTATS", where, whereArgs);

    }
}