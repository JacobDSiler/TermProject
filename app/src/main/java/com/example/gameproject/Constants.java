package com.example.gameproject;

public final class Constants {
    // Screen
    public static int SCREEN_HEIGHT = 1080;
    public static int SCREEN_WIDTH = 1920;

    // Bird
    public static int BIRD_WIDTH = 128;
    public static int BIRD_HEIGHT = 128;
    public static int BIRD_X = 100;
    public static int BIRD_Y = SCREEN_HEIGHT / 2 - BIRD_HEIGHT / 2;
    public static int BIRD_DROP = 15;
    public static double BIRD_DROP_RATE = 0.6;
    public static int BIRD_ANGLE_UP = 25;
    public static int BIRD_ANGLE_DOWN = 45;

    // Pipe
    public static int PIPE_HEIGHT = SCREEN_HEIGHT / 2;
    public static int PIPE_WIDTH = 200;
    public static int PIPE_DISTANCE = 300;
    public static int PIPE_SUM = 6;


    public static void setScreenHeight(int screenHeight) {
        SCREEN_HEIGHT = screenHeight;
    }
    public static void setScreenWidth(int screenWidth) {
        SCREEN_WIDTH = screenWidth;
    }
}
