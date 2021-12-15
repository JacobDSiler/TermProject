package com.example.gameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

/**********************************************************
 * Settings activity that provides the user the option to change
 * the volume at which the game volume is set at.
 ************************************************************/

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Set seekbar with listener
        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setProgress((int)(MenuSongService.volume * 100));
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        MenuSongService.startMenuSong();

        // Set listener for difficulty button
        Button difficulty = findViewById(R.id.toggleButton);
        difficulty.setOnClickListener(view -> {
            if (Constants.HARD) {
                Constants.setNormal();
            } else {
                Constants.setHard();
            }
        });
    }

    // Take the user back to the main menu
    public void returnToMainMenuActivity(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    /**
     * Set menu song volume based on slider amount
     */
    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) { }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            MenuSongService.setMenuSongVolume(seekBar.getProgress());
        }
    };


    /**
     * If resumed, resume music
     */
    @Override
    protected void onResume() {
        super.onResume();
        MenuSongService.startMenuSong();
    }

    /**
     * If paused, pause music
     */
    @Override
    protected void onPause() {
        super.onPause();
        MenuSongService.pauseMenuSong();
    }
}