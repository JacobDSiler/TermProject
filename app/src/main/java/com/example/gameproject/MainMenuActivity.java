package com.example.gameproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**********************************************************
 * Main menu activity where the user is directed after entering
 * a player name. The main menu provides the functionality to
 * take the user to the high scores, settings, and game screens.
 * If the database is empty this activity will initialize the
 * database with default scores for both local and global scores.
 ************************************************************/
public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Reduce the strain on the UI thread using a background thread
        SetDefaultScoresRunnable r1 = new SetDefaultScoresRunnable(this);
        Thread thread1 = new Thread(r1);
        thread1.start();
    }

    // Take the user to the settings activity
    public void openSettingsActivity(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    // Take the user to the high scores activity
    public void openHighScoresActivity(View view) {
        Intent intent = new Intent(this, HighScoresActivity.class);
        startActivity(intent);
    }

    // Take the user to play the game
    public void openGameActivity(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

}