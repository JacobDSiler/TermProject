package com.example.gameproject;

import android.app.Activity;
import android.database.Cursor;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/******************************************
 * CLASS: GetHighScoresRunnable
 * DESCRIPTION: Runnable class that runs in
 * thread to retrieve the high scores from
 * the database so the main thread can focus
 * on playing the game and responding to other
 * events
 *******************************************/

public class GetHighScoresRunnable implements Runnable {
    private DatabaseHelper myDB;
    private WeakReference<Activity> activityRef;
    private List<HighScore> highScoresList;

    public GetHighScoresRunnable(Activity activity, DatabaseHelper myDB) {
        this.activityRef = new WeakReference<Activity>(activity);
        this.myDB = myDB;
    }

    @Override
    public void run() {
        final Activity activity = activityRef.get();
        if (activity != null) {
            //highScoresList = GetHighScores(myDB);
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
            //return highScoresList;
        }
    }

    /*public List<HighScore> GetHighScores(DatabaseHelper myDB) {

    }*/

    public List<HighScore> getHighScoresList() {
        return highScoresList;
    }
}
