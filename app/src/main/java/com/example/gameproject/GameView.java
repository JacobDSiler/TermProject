package com.example.gameproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GameView extends View {
    private Bird bird;
    private Handler handler;
    private Runnable r;
    private ArrayList<Pipe> arrPipes;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initBird();
        initPipe();
        handler = new Handler();
        r = this::invalidate;
    }

    private void initPipe() {
        arrPipes = new ArrayList<>();
        for (int i = 0; i < Constants.PIPE_SUM; i++) {
            if (i < Constants.PIPE_SUM / 2) {
                this.arrPipes.add(new Pipe(
                        Constants.SCREEN_WIDTH + i*(Constants.SCREEN_WIDTH + Constants.PIPE_WIDTH)/(Constants.PIPE_SUM / 2),
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

    public void draw(Canvas canvas) {
        super.draw(canvas);
        bird.draw(canvas);
        for (int i = 0; i < Constants.PIPE_SUM; i++) {
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
        handler.postDelayed(r, 10);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()== MotionEvent.ACTION_DOWN) {
            bird.setDrop(-Constants.BIRD_DROP);
        }
        return true;
    }
}
