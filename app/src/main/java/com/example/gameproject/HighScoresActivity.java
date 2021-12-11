package com.example.gameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

 /**********************************************************
 * High scores activity that displays the local high scores of the user
 * with the accompanying player name high score rank. This activity
 * utilizes the SQLite database
 ************************************************************/
public class HighScoresActivity extends AppCompatActivity {
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        myDB = new DatabaseHelper(this);
        Cursor data = myDB.getListContents();

        // If the database is empty log the message else
        // set the textviews to the scores from the database
        // and log the name, id, and score read from the database
        if (data.getCount() == 0) {
            Log.i("HighScoreActivity", "DATABASE IS EMPTY");
        }
        else {
            setHighScores(data);
        }

        MenuSong.startMenuSong();
    }

    // Set the player name and score using the database for the user to visualize
    public void setHighScores(Cursor data) {
        Log.i("HighScoreActivity", "READING FROM DATABASE");
        int i = 0;
        List<TextView> textViewList = createTextViewList();
        // Loop through the first 6 values in the database table
        while (data.moveToNext()) {
            if (i == 12)
                break;
            else {
                String scoreId = data.getString(0);
                String scoreName = data.getString(1);
                String score = data.getString(2);
                String row = scoreId + " " + scoreName + " " + score;
                Log.i("HighScoreActivity", "Row: " + row);
                // Add the name and score to the textviews for a visual
                textViewList.get(i).setText(scoreName);
                i++;
                textViewList.get(i).setText(score);
                i++;
            }
        }
    }

    // When the back arrow button is pressed the user will be navigated back to the main menu activity
    public void returnToMainMenuActivity(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    // When the global button is pressed the user will be navigated to the global scores activity screen
    public void openGlobalHighScoresActivity(View view) {
        Intent intent = new Intent(this, GlobalHighScoresActivity.class);
        startActivity(intent);
    }

    // Function that adds all of the player name and score textviews
    // to a list so they can be written to for the user to visualize
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

     @Override
     protected void onResume() {
         super.onResume();
         MenuSong.startMenuSong();
     }

     @Override
     protected void onPause() {
         super.onPause();
         MenuSong.pauseMenuSong();
     }
}