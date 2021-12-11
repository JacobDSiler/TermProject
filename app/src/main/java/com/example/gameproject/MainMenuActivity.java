package com.example.gameproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

/**********************************************************
 * Main menu activity that provides the functionality to
 * take the user to the high scores, settings, and game screens.
 * If the database is empty this activity will initialize the
 * database with default scores for both local and global scores.
 * This activity is the starting point of the app
 ************************************************************/
public class MainMenuActivity extends AppCompatActivity {
    private MenuSong menuSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Reduce the strain on the UI thread using a background thread
        SetDefaultScoresRunnable r1 = new SetDefaultScoresRunnable(this);
        Thread thread1 = new Thread(r1);
        thread1.start();

        menuSong = new MenuSong(this);
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

    // Take the user to enter a play name before entering the game
    public void openLoginActivity(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
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