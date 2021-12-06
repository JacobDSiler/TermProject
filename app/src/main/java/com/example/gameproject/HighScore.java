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

    public HighScore(String id, String name, String score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
