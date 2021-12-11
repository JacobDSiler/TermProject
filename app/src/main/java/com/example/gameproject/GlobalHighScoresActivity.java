package com.example.gameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**********************************************************
 * Global high scores activity that displays the global high scores of the user
 * with the accompanying player name and high score rank. This activity
 * utilizes the SQLite database
 ************************************************************/
public class GlobalHighScoresActivity extends AppCompatActivity {
    private DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_high_scores);
        myDB = new DatabaseHelper(this);
        Cursor data = myDB.getListContents();
        if (data.getCount() == 0) {
            Log.i("GlobalScoresActivity", "DATABASE IS EMPTY");
        }
        else {
            setHighScores(data);
        }

        MenuSong.startMenuSong();
    }

    // Add the global high scores to the textviews for the user to visualize
    public void setHighScores(Cursor data) {
        Log.i("GlobalScoreActivity", "READING FROM DATABASE");
        int i = 0; // This is used as the index to place the values from the database into the correct textviews
        int j = 0; // used to get to the middle of the database where the global scores begin
        List<TextView> textViewList = createTextViewList();
        // Loop through the database table and add the score data from the bottom half of
        // database to the textview list
        while (data.moveToNext()) {
            if (i == 12)
                break;
            else if (j >= 6) {
                String scoreId = data.getString(0);
                String scoreName = data.getString(1);
                String score = data.getString(2);
                String row = scoreId + " " + scoreName + " " + score;
                Log.i("GlobalScoreActivity", "Row: " + row);
                textViewList.get(i).setText(scoreName);
                i++;
                textViewList.get(i).setText(score);
                i++;
            }
            j++;
        }
    }

    // Take the user to the main menu
    public void returnToMainMenuActivity(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    // Take the user to the local high scores
    public void openHighScoresActivity(View view) {
        Intent intent = new Intent(this, HighScoresActivity.class);
        startActivity(intent);
    }

    // Generate a list with the player name and score textviews
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