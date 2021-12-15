package com.example.gameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**********************************************************
 * Game activity that manages the game through the game view class.
 ************************************************************/
public class GameActivity extends AppCompatActivity {
    public static DatabaseHelper myDB;
    public static List<HighScore> highScoresList;
    public static TextView txt_score, txt_best_score, txt_score_over;
    public static RelativeLayout rl_game_over;
    public static Button btn_start;
    private GameView gameView;
    public static MediaPlayer mediaPlayer;


    /**
     * @param savedInstanceState Bundle for super
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);

        // Sets the display height and width
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.setScreenWidth(dm.widthPixels);
        Constants.setScreenHeight(dm.heightPixels);

        // Identifies the fields and buttons to set values and actions to

        txt_score = findViewById(R.id.txt_score);
        txt_best_score = findViewById(R.id.txt_best_score);
        txt_score_over = findViewById(R.id.txt_score_over);
        txt_score = findViewById(R.id.txt_score);
        rl_game_over = findViewById(R.id.rl_game_over);
        btn_start = findViewById(R.id.btn_start);
        gameView = findViewById(R.id.gameView);

        // Start the game
        btn_start.setOnClickListener(v -> {
            gameView.setStart(true);
            txt_score.setVisibility(View.VISIBLE);
            btn_start.setVisibility(View.INVISIBLE);
        });

        // Reset the game variables and play the game again
        rl_game_over.setOnClickListener(v -> {
            btn_start.setVisibility(View.VISIBLE);
            rl_game_over.setVisibility(View.INVISIBLE);
            gameView.setStart(false);
            gameView.reset();
        });

        // Get high scores
        myDB = new DatabaseHelper(this);
        highScoresList = GetHighScores(myDB);

        // Start game background music
        mediaPlayer = MediaPlayer.create(this, R.raw.game_song);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    /**
     * Used to pause music when activity is paused
     */
    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

    /**
     * Used to resume music when activity is resumed
     */
    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
    }


    /**
     * Take the user back to the main menu
     * @param view
     */
    public void returnToMainMenuActivity(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }


    /**
     * Reduce the strain on the UI thread by running a background thread
     * to get the high scores from the database table
     * @param myDB DatabaseHelper to pass into Runnable
     * @return List of HighScore
     */
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