package com.example.gameproject;

import android.app.Activity;
import android.database.Cursor;
import java.lang.ref.WeakReference;

/******************************************
* CLASS: SetDefaultScoresRunnable
* DESCRIPTION: Runnable class that is used
* to write default high scores to the database
* when the player starts the game for the first
* time and there is nothing in the database. This
* runnable will run in a thread to prevent the main
* UI thread from getting bogged down
*******************************************/

public class SetDefaultScoresRunnable implements Runnable {
    private DatabaseHelper myDB;
    private WeakReference<Activity> activityRef;

    public SetDefaultScoresRunnable(Activity activity) {
        this.activityRef = new WeakReference<Activity>(activity);
    }

    @Override
    public void run() {
        final Activity activity = activityRef.get();
        if (activity != null) {
            myDB = new DatabaseHelper(activity);
            Cursor data = myDB.getListContents();
            if (data.getCount() == 0) {
                setDefaultHighScores();
            }
        }
    }

    // Adds default high scores to the database
    public void setDefaultHighScores() {
        myDB.addData("0", "Abby", "4");
        myDB.addData("1", "Jake", "3");
        myDB.addData("2", "Michael", "2");
        myDB.addData("3", "Paul", "2");
        myDB.addData("4", "Sarah", "1");
        myDB.addData("5", "Sam", "1");
    }
}
