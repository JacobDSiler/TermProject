package com.example.gameproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainMenuActivity extends AppCompatActivity {
    private DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DatabaseHelper(this);
        Cursor data = myDB.getListContents();
        if (data.getCount() == 0) {
            // TODO: Create a thread for this occur
            setDefaultHighScores();
        }

    }

    public void openSettingsActivity(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void openHighScoresActivity(View view) {
        Intent intent = new Intent(this, HighScoresActivity.class);
        startActivity(intent);
    }

    public void openGameActivity(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    // Put this in a thread
    public void setDefaultHighScores() {
        // Write the default scores to the database. Perhaps put this in a thread
        myDB.addData("0", "Abby", "20");
        myDB.addData("1", "Jake", "18");
        myDB.addData("2", "Michael", "16");
        myDB.addData("3", "Paul", "14");
        myDB.addData("4", "Sarah", "12");
        myDB.addData("5", "Sam", "10");
    }
}