package com.example.gameproject;

import static java.lang.String.*;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GameView extends View {
    private Bird bird;
    private Handler handler;
    private Runnable r;
    private ArrayList<Pipe> arrPipes;
    private int score, bestScore = 0;
    private boolean start;
    private Context context;
    private int soundJump;
    private int soundDeath;
    private boolean collision;
    private float volume;
    private boolean loadedSound;
    private SoundPool soundPool;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        Constants.setNormal();

        this.context = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences("gamesettings", Context.MODE_PRIVATE);
        if (sharedPreferences != null) {
            bestScore = sharedPreferences.getInt("bestScore", 0);
        }

        score = 0;
        collision = false;
        bestScore = 0;
        start = false;

        initBird();
        initPipe();
        handler = new Handler();
        r = this::invalidate;


        SoundPool.Builder builder = new SoundPool.Builder();

        AudioAttributes audioAttributes = new AudioAttributes
                .Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        builder.setAudioAttributes(audioAttributes).setMaxStreams(5);

        this.soundPool = builder.build();

        this.soundPool.setOnLoadCompleteListener((soundPool, sampleId, status) -> loadedSound = true);
        soundJump = this.soundPool.load(context, R.raw.jump_02, 1);
        soundDeath = this.soundPool.load(context, R.raw.death, 1);
    }

    public void setScore(int num) { this.score = num; }
    public int getScore() { return score; }
    public boolean isCollision() { return collision; }
    public void setCollision(boolean collide) { this.collision = collide; }

    private void initPipe() {
        arrPipes = new ArrayList<>();
        for (int i = 0; i < Constants.PIPE_SUM; i++) {

            if (i < Constants.PIPE_SUM / 2) {
                this.arrPipes.add(new Pipe(
                        Constants.SCREEN_WIDTH + (float)i * (Constants.SCREEN_WIDTH + Constants.PIPE_WIDTH)/(float)(Constants.PIPE_SUM / 2),
                        0,
                        Constants.PIPE_WIDTH,
                        Constants.PIPE_HEIGHT)
                );
                this.arrPipes.get(this.arrPipes.size()-1).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.pipe_upside_down));
                this.arrPipes.get(this.arrPipes.size()-1).randomY();
            } else {
                this.arrPipes.add(new Pipe(
                            this.arrPipes.get(i - Constants.PIPE_SUM / 2).getX(),
                            this.arrPipes.get(i - Constants.PIPE_SUM / 2).getY() + this.arrPipes.get(i-Constants.PIPE_SUM / 2).getHeight() + Constants.PIPE_DISTANCE,
                            Constants.PIPE_WIDTH,
                            Constants.PIPE_HEIGHT
                        )
                );
                this.arrPipes.get(this.arrPipes.size()-1).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.pipe));
            }
        }
    }

    private void initBird() {
        bird = new Bird();
        bird.setWidth(Constants.BIRD_WIDTH);
        bird.setHeight(Constants.BIRD_HEIGHT);
        bird.setX(Constants.BIRD_X);
        bird.setY(Constants.BIRD_Y);
        ArrayList<Bitmap> arrBms = new ArrayList<>();
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.frame_1));
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.frame_2));
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.frame_3));
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.frame_4));
        bird.setArrBms(arrBms);
    }

    public void saveLocalNewHighScore(int newScore, String newName) {
        for (int i = 0; i < 6; i++) {
        if (Integer.parseInt(GameActivity.highScoresList.get(i).getScore()) < newScore) {
                String name = GameActivity.highScoresList.get(i).getName();
                String score = GameActivity.highScoresList.get(i).getScore();
                String id = GameActivity.highScoresList.get(i).getId();
                String newScoreStr = Integer.toString(newScore);
                Log.i("GameView", id + " " + newName + " " + newScoreStr);
                GameActivity.myDB.updateHighScore(id, name, score, newScoreStr);
                GameActivity.myDB.updateHighScoreName(id, name, newScoreStr, newName);
                break;
            }
        }
    }

    public void saveGlobalNewHighScore(int newScore, String username) {
        for (int i = 6; i < 12; i++) {
            if (Integer.parseInt(GameActivity.highScoresList.get(i).getScore()) < newScore) {
                String name = GameActivity.highScoresList.get(i).getName();
                String score = GameActivity.highScoresList.get(i).getScore();
                String id = GameActivity.highScoresList.get(i).getId();
                String newScoreStr = Integer.toString(newScore);
                Log.i("GameView", id + " " + username + " " + newScoreStr);
                GameActivity.myDB.updateHighScore(id, name, score, newScoreStr);
                GameActivity.myDB.updateHighScoreName(id, name, newScoreStr, username);
                break;
            }
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (start) {
            bird.draw(canvas, true);
            for (int i = 0; i < Constants.PIPE_SUM; i++) {

                // If collision with pipe, top of screen, or bottom of screen
                if (bird.getRect().intersect(arrPipes.get(i).getRect()) || bird.getY() - bird.getHeight() < 0 || bird.getY() > Constants.SCREEN_HEIGHT) {
                    Pipe.speed = 0;
                    bestScore = Integer.parseInt(GameActivity.highScoresList.get(0).getScore());
                    GameActivity.txt_score_over.setText(GameActivity.txt_score.getText());
                    GameActivity.txt_best_score.setText(format("Best: %d", bestScore));
                    GameActivity.txt_score.setVisibility(INVISIBLE);
                    GameActivity.rl_game_over.setVisibility(VISIBLE);
                    if (!isCollision()) {
                        SharedPreferences sp = context.getSharedPreferences("username", Context.MODE_PRIVATE);
                        String playerName = sp.getString("player", "");

                        // If new high score
                        int lowestLocalScore = Integer.parseInt(GameActivity.highScoresList.get(5).getScore());
                        int lowestGlobalScore = Integer.parseInt(GameActivity.highScoresList.get(11).getScore());
                        if (score > lowestLocalScore) {
                            saveLocalNewHighScore(score, playerName);
                        }
                        if (score > lowestGlobalScore) {
                            saveGlobalNewHighScore(score, playerName);
                        }
                        setCollision(true);
                        GameActivity.mediaPlayer.pause();
                        if (loadedSound) {
                            this.soundPool.play(this.soundDeath, (float).5, (float)0.5, 1, 0, 1f);
                        }
                    }
                }

                // If Bird is at middle of x for pipe
                if (this.bird.getX() + this.bird.getWidth() / 2.0 > arrPipes.get(i).getX() + arrPipes.get(i).getWidth() / 2.0
                        && this.bird.getX() + this.bird.getWidth() / 2.0 <= arrPipes.get(i).getX() + arrPipes.get(i).getWidth() / 2.0 + Pipe.speed
                        && i < Constants.PIPE_SUM / 2) {
                    score++;
                    GameActivity.txt_score.setText(format("%d", score));

                }

                if (arrPipes.get(i).getX() < -arrPipes.get(i).getWidth()) {
                    arrPipes.get(i).setX(Constants.SCREEN_WIDTH);
                    if (i < Constants.PIPE_SUM / 2) {
                        arrPipes.get(i).randomY();
                    } else {
                        arrPipes.get(i).setY(arrPipes.get(i - Constants.PIPE_SUM / 2).getY() + arrPipes.get(i - Constants.PIPE_SUM / 2).getHeight() + Constants.PIPE_DISTANCE);
                    }
                }
                arrPipes.get(i).draw(canvas);
            }
        } else {
            if (bird.getY() > Constants.SCREEN_HEIGHT) {
                bird.setDrop((float) (-15*Constants.SCREEN_HEIGHT/1920));
            }
            bird.draw(canvas, false);
        }
        handler.postDelayed(r, 10);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            bird.setDrop(-Constants.BIRD_DROP);
            if (loadedSound) {
                this.soundPool.play(this.soundJump, (float).5, (float)0.5, 1, 0, 1f);
            }
        }
        return true;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public void reset() {
        GameActivity.txt_score.setText("0");
        setCollision(false);
        GameActivity.mediaPlayer.start();
        setScore(0);
        initPipe();
        initBird();
    }
}
