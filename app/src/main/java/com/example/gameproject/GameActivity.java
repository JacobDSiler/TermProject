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
    private DatabaseHelper myDB;
    //Map<String, List<String>> highScores;
    private List<HighScore> highScoresList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.setScreenWidth(dm.widthPixels);
        Constants.setScreenHeight(dm.heightPixels);

        myDB = new DatabaseHelper(this);
        highScoresList = GetHighScores(myDB);

        // TODO: check if the player died right here. If they did then get the score
        int newScore = 21;
        int lowestScore = Integer.parseInt(highScoresList.get(5).getScore());
        if (lowestScore < newScore) {
            // TODO: Have pop up box appear to get the name from the user then pass it below
            saveNewHighScore(newScore, "Jill");
        }
    }

    public void saveNewHighScore(int newScore, String newName) {
        for (int i = 0; i < highScoresList.size(); i++) {
            if (Integer.parseInt(highScoresList.get(i).getScore()) < newScore) {
                String name = highScoresList.get(i).getName();
                String score = highScoresList.get(i).getScore();
                String id = highScoresList.get(i).getId();
                String newScoreStr = Integer.toString(newScore);
                Log.i("GameActivity", id + " " + newName + " " + newScoreStr);
                myDB.updateHighScore(id, name, score, newScoreStr);
                myDB.updateHighScoreName(id, name, newScoreStr, newName);
                break;
            }
        }
    }

    public List<HighScore> GetHighScores(DatabaseHelper myDB) {
        GetHighScoresRunnable r1 = new GetHighScoresRunnable(this, myDB);
        Thread thread1 = new Thread(r1);
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return r1.getHighScoresList();
    }
}