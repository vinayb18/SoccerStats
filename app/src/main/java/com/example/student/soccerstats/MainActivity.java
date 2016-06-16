package com.example.student.soccerstats;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private EditText edtxtName;
    private EditText edtxtTeam;
    private EditText edtxtCountry;
    private EditText edtxtPosition;
    private EditText edtxtAge;
    private TextView txtvwResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtxtName = (EditText) findViewById(R.id.edtxtName);
        edtxtTeam = (EditText) findViewById(R.id.edtxtTeam);
        edtxtCountry = (EditText) findViewById(R.id.edtxtCountry);
        edtxtPosition = (EditText) findViewById(R.id.edtxtPosition);
        edtxtAge = (EditText) findViewById(R.id.edtxtAge);

        txtvwResult = (TextView) findViewById(R.id.txtvwResult);
    }

    public void addPlayer(View vw){

        SoccerDatabaseHelper soccerDatabaseHelper = new SoccerDatabaseHelper(this,null,null,0);
        SQLiteDatabase db;


        String name = edtxtName.getText().toString();
        String team = edtxtTeam.getText().toString();
        String country = edtxtCountry.getText().toString();
        String position = edtxtPosition.getText().toString();
        String age = edtxtAge.getText().toString();
        ContentValues player = new ContentValues();

        if(name.equals("") || team.equals("")){

            txtvwResult.setText("No name or team given");
        } else {



        }


        try {
            db = soccerDatabaseHelper.getWritableDatabase();

            //insert code from if-else statement here
            if (edtxtName.getText().length() == 0 || edtxtPosition.getText().length() == 0 ||
                    edtxtTeam.getText().length() == 0 || edtxtAge.getText().length() == 0||
                    edtxtCountry.getText().length() == 0){

                txtvwResult.setText("You must enter all values to add an element!");

            } else {

                /**
                 * 1. Set each variable equal to the values from the EditTexts
                 * 2. put each value into the ContentValues variable
                 * 3. Call the EmployeeDatabaseHelper's insertElement method
                 * 4. Display that the element has been added successfully
                 */

                name = edtxtName.getText().toString();
                position = edtxtPosition.getText().toString();
                team = edtxtTeam.getText().toString();
                age = edtxtAge.getText().toString();
                country = edtxtCountry.getText().toString();

                player.put("NAME", name);
                player.put("TEAM", team);
                player.put("COUNTRY", country);
                player.put("POSITION", position);
                player.put("AGE", age);


                soccerDatabaseHelper.insertElement(db, player);
                soccerDatabaseHelper.close();

                txtvwResult.setText("Successfully Added");
            }

            }
            catch(SQLiteException sqlE)
            {
                txtvwResult.setText("Database Not Found");
            }
        }

    public void clearDatabase(View vw){

    }

    public void goToNext (View vw) {

        Intent nextScreen = new Intent(this, DisplayStats.class);

        startActivity(nextScreen);

    }
}
