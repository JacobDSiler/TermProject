package com.example.gameproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import java.util.ArrayList;

public class Bird extends BaseObject {
    private ArrayList<Bitmap> arrBms = new ArrayList<>();
    private final int flapCount;
    private int count;
    private int idCurrentBitmap;
    private float drop;

    /**
     * Constructor sets default Bird
     */
    public Bird() {
        this.count = 0;
        this.flapCount = 5;
        this.idCurrentBitmap = 0;
        this.drop = 0;
    }

    /**
     * @param arrBms ArrayList of Bitmaps, used to set sprite frames for the bird
     */
    public void setArrBms(ArrayList<Bitmap> arrBms) {
        this.arrBms = arrBms;
        for (int i = 0; i < arrBms.size(); i++) {
            this.arrBms.set(i, Bitmap.createScaledBitmap(this.arrBms.get(i), width, height, true));
        }
    }

    /**
     * @param drop float used to set drop of bird
     */
    public void setDrop(float drop) {
        this.drop = drop;
    }

    /**
     * @return Bitmap of bird
     */
    @Override
    public Bitmap getBm() {
        Matrix matrix = new Matrix();
        count++;

        if (count == flapCount) {
            for (int i = 0; i < arrBms.size(); i++) {
                if (i == arrBms.size() - 1) {
                    idCurrentBitmap = 0;
                    break;
                } else if (idCurrentBitmap == i) {
                    idCurrentBitmap = i + 1;
                    break;
                }
            }
            count = 0;
        }


        if (drop < 0) {
            matrix.postRotate(-Constants.BIRD_ANGLE_UP);
        } else {
            if (drop < 70) {
                matrix.postRotate(-Constants.BIRD_ANGLE_UP + (drop * 2));
            } else {
                matrix.postRotate(Constants.BIRD_ANGLE_DOWN);
            }
        }
        return Bitmap.createBitmap(
                arrBms.get(idCurrentBitmap),
                0,
                0,
                arrBms.get(idCurrentBitmap).getWidth(),
                arrBms.get(idCurrentBitmap).getHeight(),
                matrix,
                true
        );
    }

    /**
     * @param canvas Canvas for drawing bird
     * @param falling Boolean to check if falling
     */
    public void draw(Canvas canvas, Boolean falling) {
        if (falling) {
            drop();
        }
        canvas.drawBitmap(this.getBm(), x, y, null);
    }

    /**
     * Increases drop velocity and moves the height of the bird down
     */
    private void drop() {
        drop += Constants.BIRD_DROP_RATE;
        y += drop;
    }
}
