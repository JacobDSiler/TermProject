package com.example.gameproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openSettingsActivity(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void openHighScoresActivity(View view) {
        Intent intent = new Intent(this, HighScoresActivity.class);
        startActivity(intent);
    }

    public void openGameActivity(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}