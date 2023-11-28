package org.example.score;

public class TimeAttackScore extends Score{
    private static final String SCORE_NAME = "timeAttackHighScore";
    @Override
    public String getHighScore() {
        String score = bringScore(SCORE_NAME);
        return score;
    }

    @Override
    public void saveScore(String score) {
        int curScore = Integer.parseInt(score);
        int highScore = Integer.parseInt(getHighScore());
        if(curScore > highScore)    pushHighScore(SCORE_NAME, score);
    }
}
