package com.example.gameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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
            setHighScores(data);
        }
    }

    public void setHighScores(Cursor data) {
        Log.i("HighScoreActivity", "READING FROM DATABASE");
        int i = 0;
        List<TextView> textViewList = createTextViewList();
        while (data.moveToNext()) {
            String scoreId = data.getString(0);
            String scoreName = data.getString(1);
            String score = data.getString(2);
            String row = scoreId + " " + scoreName + " " + score;
            Log.i("HighScoreActivity", "Row: " + row);
            textViewList.get(i).setText(scoreName);
            i++;
            textViewList.get(i).setText(score);
            i++;
        }
    }

    public void returnToMainMenuActivity(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    // Potentially change this a linear layout or some other view
    public List<TextView> createTextViewList() {
        List<TextView> textViewList = new ArrayList<TextView>(12);
        TextView scoreView = findViewById(R.id.score1View);
        TextView playerView = findViewById(R.id.player1View);
        textViewList.add(playerView);
        textViewList.add(scoreView);

        scoreView = findViewById(R.id.score2View);
        playerView = findViewById(R.id.player2View);
        textViewList.add(playerView);
        textViewList.add(scoreView);

        scoreView = findViewById(R.id.score3View);
        playerView = findViewById(R.id.player3View);
        textViewList.add(playerView);
        textViewList.add(scoreView);

        scoreView = findViewById(R.id.score4View);
        playerView = findViewById(R.id.player4View);
        textViewList.add(playerView);
        textViewList.add(scoreView);

        scoreView = findViewById(R.id.score5View);
        playerView = findViewById(R.id.player5View);
        textViewList.add(playerView);
        textViewList.add(scoreView);

        scoreView = findViewById(R.id.score6View);
        playerView = findViewById(R.id.player6View);
        textViewList.add(playerView);
        textViewList.add(scoreView);
        return textViewList;
    }
}