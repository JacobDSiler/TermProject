package com.example.gameproject;

import android.graphics.Point;

public class Player {
    private Point point;
    private int lifeCount;

    public Player() {
        this.point = new Point();
        this.lifeCount = 3;
    }

    public Player(Point point, int lifeCount) {
        this.point = point;
        this.lifeCount = lifeCount;
    }

    public int getLifeCount() {
        return lifeCount;
    }

    public void setLifeCount(int lifeCount) {
        this.lifeCount = lifeCount;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
