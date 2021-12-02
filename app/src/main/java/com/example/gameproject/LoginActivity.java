package com.example.gameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText playername = findViewById(R.id.edit_playername);
        Button saveplayername = findViewById(R.id.savePlayerName);
        saveplayername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(playername.getText().toString()))
                    Toast.makeText(LoginActivity.this, "Enter a player name", Toast.LENGTH_SHORT).show();
                else if (playername.getText().toString().length() > 12)
                    Toast.makeText(LoginActivity.this, "Enter a player name less than 12 characters", Toast.LENGTH_LONG).show();
                else {
                    // save the player name to shared preferences
                    String username = playername.getText().toString();
                    sp = getSharedPreferences("username", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("player", username);
                    editor.commit();
                    openMainMenuActivity();
                }
            }
        });

    }

    public void openMainMenuActivity() {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}