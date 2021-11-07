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

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bird = new Bird();
        bird.setWidth(128);
        bird.setHeight(128);
        bird.setX(100);
        bird.setY(500-bird.getHeight());
        ArrayList<Bitmap> arrBms = new ArrayList<>();
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.frame_1));
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.frame_2));
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.frame_3));
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.frame_4));
        bird.setArrBms(arrBms);
        handler = new Handler();
        r = this::invalidate;
    }
    public void draw(Canvas canvas) {
        super.draw(canvas);
        bird.draw(canvas);
        handler.postDelayed(r, 10);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()== MotionEvent.ACTION_DOWN) {
            bird.setDrop(-15);
        }
        return true;
    }
}
