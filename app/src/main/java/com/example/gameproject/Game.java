package com.example.gameproject;

public class Game {
    private int scoreCount;
    private Player player;
    // TODO: Add obstacles but not sure if it should be a list of obstacles

    public Game() {
        this.scoreCount = 0;
        this.player = new Player();
    }

    public Game(int scoreCount, Player player) {
        this.scoreCount = scoreCount;
        this.player = player;
    }

    public int getScoreCount() {
        return scoreCount;
    }

    public void setScoreCount(int scoreCount) {
        this.scoreCount = scoreCount;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
