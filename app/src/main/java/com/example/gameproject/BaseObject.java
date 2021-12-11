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
     */
    public BaseObject(float x, float y, int width, int height, Bitmap bm) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bm = bm;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Bitmap getBm() {
        return bm;
    }

    public void setBm(Bitmap bm) {
        this.bm = bm;
    }

    public Rect getRect() {
        return new Rect((int) this.x, (int) this.y, (int) this.x + this.width, (int) this.y + this.height);
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }
}
