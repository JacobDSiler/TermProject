package com.example.gameproject;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * BaseObject is used as the parent for objects in the game. Creates the position,
 * size, and bitmap for every object
 */
public class BaseObject {
    protected float x, y;
    protected int width, height;
    protected Rect rect;
    protected Bitmap bm;


    public BaseObject() { }

    /**
     * Constructor for BaseObject
     *
     * @param x x coordinate for object
     * @param y y coordinate for object
     * @param width width of object
     * @param height height of object
     */
    public BaseObject(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Constructor for BaseObject
     *
     * @param x x coordinate for object
     * @param y y coordinate for object
     * @param width width of object
     * @param height height of object
     * @param bm bitmap for object
     */
    public BaseObject(float x, float y, int width, int height, Bitmap bm) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bm = bm;
    }

    /**
     * @return x
     */
    public float getX() {
        return x;
    }

    /**
     * @param x float to set x
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * @return y
     */
    public float getY() {
        return y;
    }

    /**
     * @param y float to set y
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width int to set width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height int to set height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return bm
     */
    public Bitmap getBm() {
        return bm;
    }

    /**
     * @param bm Bitmap to set bm
     */
    public void setBm(Bitmap bm) {
        this.bm = bm;
    }

    /**
     * @return 2D Rect of object
     */
    public Rect getRect() {
        return new Rect((int) this.x, (int) this.y, (int) this.x + this.width, (int) this.y + this.height);
    }
}
