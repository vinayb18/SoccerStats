package com.example.student.soccerstats;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by student on 14/06/2016.
 */
public class DisplayStats extends AppCompatActivity
{
    TextView txtvwStatArea;

    private SQLiteDatabase db;
    private SoccerDatabaseHelper sccrDatabaseHelper;
    private Cursor crsrDBReader;
    private ArrayList<String> arylstAllItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_stats);


        txtvwStatArea = (TextView) findViewById(R.id.txtvwStatArea);
    }

    public boolean showStats(View vw)
    {
        sccrDatabaseHelper = new SoccerDatabaseHelper(DisplayStats.this, null, null, 0);
        arylstAllItems = new ArrayList<String>();

        try
        {
            db = sccrDatabaseHelper.getReadableDatabase();
        }
        catch(SQLiteException sqlEx)
        {
            return false;
        }

        crsrDBReader = db.rawQuery("SELECT * FROM SOCCERSTATS", null);

        if(crsrDBReader != null)
        {
            if(crsrDBReader.moveToFirst())
            {
                while(crsrDBReader.isAfterLast() == false)
                {
                    String strName = crsrDBReader.getString(crsrDBReader.getColumnIndex("NAME"));

                    arylstAllItems.add(strName);

                    String strTeam = crsrDBReader.getString(crsrDBReader.getColumnIndex("TEAM"));

                    arylstAllItems.add(strTeam);

                    String strCountry = crsrDBReader.getString(crsrDBReader.getColumnIndex("COUNTRY"));

                    arylstAllItems.add(strCountry);

                    String strPosition = crsrDBReader.getString(crsrDBReader.getColumnIndex("POSITION"));

                    arylstAllItems.add(strPosition);

                    Integer intAge = crsrDBReader.getInt(crsrDBReader.getColumnIndex("AGE"));

                    arylstAllItems.add(intAge.toString());

                    crsrDBReader.moveToNext();
                }
            }
            crsrDBReader.close();
        }

        sccrDatabaseHelper.close();

        txtvwStatArea.setText(arylstAllItems.toString());

        return true;

    }


}
