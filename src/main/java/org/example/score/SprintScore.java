package org.example.score;

import java.util.Objects;

public class SprintScore extends Score{
    private static final String SCORE_NAME = "sprintHighScore";
    @Override
    public String getHighScore() {
        String score = bringScore(SCORE_NAME);
        if(Objects.equals(score, "99:99")) return "NA";

        return score;
    }

    private int scoreToSec(String MMSS){
        String mm = MMSS.substring(0,2);
        String ss = MMSS.substring(3,5);
        int minute = Integer.parseInt(mm);
        int sec = Integer.parseInt(ss);

        return minute*60+sec;
    }

    @Override
    public void saveScore(String score) {
        int curScore = scoreToSec(score);
        int highScore = scoreToSec(bringScore(SCORE_NAME));

        if(curScore < highScore)    pushHighScore(SCORE_NAME, score);
    }
}
