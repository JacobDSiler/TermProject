package com.example.gameproject;

import android.graphics.Point;

public class Obstacle {
    private Point point;

    public Obstacle() {
        this.point = new Point();
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
