package com.example.hangmanpoging2;

/**
 * Created by Tessa on 21-1-14.
 */
public class Scores {

    private int id;
    private int scores;

    public Scores(){}

    //getters & setters
    public Scores(Integer scores) {
        super();
        this.scores = scores;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Integer getScores() {
        return scores;
    }

    public void setScores(Integer scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "Scores [id=" + id + ", scores=" + scores + "]";
    }
}