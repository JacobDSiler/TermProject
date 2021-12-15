package com.example.gameproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

/**
 * Pipe used in game
 */
public class Pipe extends BaseObject {
    public static int speed;

    /**
     * @param x float used for super
     * @param y float used for super
     * @param width int used for super
     * @param height int used for super
     */
    public Pipe(float x, float y, int width, int height) {
        super(x, y, width, height);
        speed = Constants.PIPE_SPEED;
    }

    /**
     * @param canvas Canvas used to draw sprite
     */
    public void draw(Canvas canvas) {
        this.x-=speed;
        canvas.drawBitmap(this.bm, this.x, this.y, null);
    }

    /**
     * Generate random height for pipe
     */
    public void randomY() {
        Random r = new Random();
        this.y = r.nextInt((this.height / Constants.PIPE_VARIANCE) + 1) - this.height / Constants.PIPE_VARIANCE;
    }

    /**
     * @param bm Bitmap to set bm
     */
    @Override
    public void setBm(Bitmap bm) {
        this.bm = Bitmap.createScaledBitmap(bm, width, height, true);
    }
}
