package com.example.gameproject;

public final class Constants {
    // General
    public static int VOLUME = 100;

    // Screen
    public static int SCREEN_HEIGHT = 1080;
    public static int SCREEN_WIDTH = 1920;

    // Bird
    public static int BIRD_WIDTH = 98;
    public static int BIRD_HEIGHT = 98;
    public static int BIRD_X = 100;
    public static int BIRD_Y = SCREEN_HEIGHT / 2 - BIRD_HEIGHT / 2;
    public static int BIRD_DROP = 15;
    public static double BIRD_DROP_RATE = 0.6;
    public static int BIRD_ANGLE_UP = 25;
    public static int BIRD_ANGLE_DOWN = 45;

    // Pipe
    public static int PIPE_HEIGHT = SCREEN_HEIGHT / 2;
    public static int PIPE_WIDTH = 200;
    public static int PIPE_DISTANCE = 325;
    public static int PIPE_SUM = 6;
    public static int PIPE_SPEED = 12;
    public static int PIPE_VARIANCE = 3;


    public static void setScreenHeight(int screenHeight) {
        SCREEN_HEIGHT = screenHeight;
    }
    public static void setScreenWidth(int screenWidth) {
        SCREEN_WIDTH = screenWidth;
    }

    public static void setEasy() {
        PIPE_SUM = 4;
        PIPE_DISTANCE = 400;
        PIPE_WIDTH = 150;
        PIPE_VARIANCE = 4;
    }

    public static void setNormal() {
        PIPE_SUM = 6;
        PIPE_DISTANCE = 325;
        PIPE_WIDTH = 200;
        PIPE_VARIANCE = 3;
    }

    public static void setHard() {
        PIPE_SUM = 6;
        PIPE_DISTANCE = 275;
        PIPE_WIDTH = 225;
        BIRD_DROP_RATE = 0.75;
        PIPE_SPEED = 20;
        PIPE_VARIANCE = 3;
        BIRD_DROP = 18;
    }

    public static void setIronBird() {
        BIRD_DROP_RATE = 1.25;
    }
}
