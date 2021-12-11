package com.example.gameproject;

/**********************************************************
 * CLASS: High Score
 * DESCRIPTION: Class object that's used for storing and collecting
 * player high scores. The object includes an id, name, score.
 ************************************************************/
public class HighScore {
    private String id;
    private String name;
    private String score;

    // Constructor to initialize the attribute variables
    public HighScore(String id, String name, String score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    // Get the high score id
    public String getId() {
        return id;
    }

    // Set the high score id
    public void setId(String id) {
        this.id = id;
    }

    // Get the high score player name
    public String getName() {
        return name;
    }

    // Set the high score player name
    public void setName(String name) {
        this.name = name;
    }

    // Get the high score player score
    public String getScore() {
        return score;
    }

    // Set the high score player score
    public void setScore(String score) {
        this.score = score;
    }
}
