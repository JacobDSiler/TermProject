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

    // Constructor to initialize database object and activity reference
    public GetHighScoresRunnable(Activity activity, DatabaseHelper myDB) {
        this.activityRef = new WeakReference<Activity>(activity);
        this.myDB = myDB;
    }

    @Override
    public void run() {
        final Activity activity = activityRef.get();
        if (activity != null) {
            highScoresList = new ArrayList<HighScore>(12);
            // Get the contents from the database
            Cursor data = myDB.getListContents();

            // Traverse the data from the database and add it to a list of high score objects with some logging
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

    // Return a list of high score objects to an activity
    public List<HighScore> getHighScoresList() {
        return highScoresList;
    }
}
