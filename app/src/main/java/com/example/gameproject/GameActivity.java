package com.example.gameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    //Map<String, List<String>> highScores;
    List<HighScore> highScoresList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.setScreenWidth(dm.widthPixels);
        Constants.setScreenHeight(dm.heightPixels);

        /* TODO: In a background thread read the contents from the database into a collection
         * of sorts and then when the player dies check if the score they got is higher than the
         * lowest high score. If it is then have a pop up box appear that will have them enter
         * a name associated with the new high score and then write the updated scores back to the
         * database in the correct order.
         */
        myDB = new DatabaseHelper(this);
        highScoresList = new ArrayList<HighScore>(6);
        // put this in a thread and return the list back to the main thread
        Cursor data = myDB.getListContents();
        while (data.moveToNext()) {
            String scoreId = data.getString(0);
            String scoreName = data.getString(1);
            String score = data.getString(2);
            Log.i("GameActivity", scoreId + " " + scoreName + " " + score);
            HighScore highscore = new HighScore(scoreId, scoreName, score);
            highScoresList.add(highscore);
        }
        // check if the player died right here
        int newScore = 21;
        int lowestScore = Integer.parseInt(highScoresList.get(5).getScore());
        if (lowestScore < newScore) {
            // pop up box here to get the name from the user
            for (int i = 0; i < highScoresList.size(); i++) {
                if (Integer.parseInt(highScoresList.get(i).getScore()) < newScore) {
                    String name = highScoresList.get(i).getName();
                    String score = highScoresList.get(i).getScore();
                    String id = highScoresList.get(i).getId();
                    myDB.updateHighScore(id, name, score, Integer.toString(newScore));
                    break;
                }
            }
        }

    }
}