package com.example.gameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class HighScoresActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        myDB = new DatabaseHelper(this);
        Cursor data = myDB.getListContents();
        if (data.getCount() == 0) {
            Log.i("HighScoreActivity", "DATABASE IS EMPTY");
        }
        else {
            Log.i("HighScoreActivity", "READING FROM DATABASE");
            while (data.moveToNext()) {
                String scoreId = data.getString(1);
                String scoreName = data.getString(2);
                String score = data.getString(3);
                String row = scoreId + " " + scoreName + " " + score;
                Log.i("HighScoreActivity", "Row: " + row);
            }
        }
    }

    public void returnToMainMenuActivity(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}