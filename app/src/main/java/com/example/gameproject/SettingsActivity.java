package com.example.gameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

/**********************************************************
 * Settings activity that provides the user the option to change
 * the volume at which the game volume is set at.
 ************************************************************/

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        MenuSong.startMenuSong();
    }

    // Take the user back to the main menu
    public void returnToMainMenuActivity(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
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