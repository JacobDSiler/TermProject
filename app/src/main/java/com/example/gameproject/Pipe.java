package com.example.gameproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

public class Pipe extends BaseObject {
    public static int speed;

    public Pipe(float x, float y, int width, int height) {
        super(x, y, width, height);
        speed = Constants.PIPE_SPEED;
    }

    public void draw(Canvas canvas) {
        this.x-=speed;
        canvas.drawBitmap(this.bm, this.x, this.y, null);
    }

    public void randomY() {
        Random r = new Random();
        this.y = r.nextInt((this.height / Constants.PIPE_VARIANCE) + 1) - this.height / Constants.PIPE_VARIANCE;
    }

    @Override
    public void setBm(Bitmap bm) {
        this.bm = Bitmap.createScaledBitmap(bm, width, height, true);
    }
}
