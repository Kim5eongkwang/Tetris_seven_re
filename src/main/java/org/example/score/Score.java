package org.example.score;

public abstract class Score {
    private String scoreName;

    public void setScoreName(String name){
        this.scoreName = name;
    }
    public String getScoreName(){
        return scoreName;
    }

    public String getScore(){
        return "구현해 json으로";
    }

    public abstract void saveScore(String score);
}
