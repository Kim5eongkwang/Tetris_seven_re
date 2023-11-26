package org.example.score;

public class TimeAttackScore extends Score{
    private static final String scoreName = "timeAttackHighScore";
    @Override
    public String getHighScore() {
        String score = BringScore(scoreName);
        return score;
    }

    @Override
    public void saveScore(String score) {
        int curScore = Integer.parseInt(score);
        int highScore = Integer.parseInt(getHighScore());
        if(curScore > highScore)    pushHighScore(scoreName, score);
    }
}
