package org.example.score;

public class SprintScore extends Score{
    private static final String scoreName = "sprintHighScore";
    @Override
    public String getHighScore() {
        String score = BringScore(scoreName);
        if(Integer.parseInt(score) == 999999) return "NA";

        return score;
    }

    @Override
    public void saveScore(String score) {
        String mm = score.substring(0,1);
        String ss = score.substring(3,4);
        int minute = Integer.parseInt(mm);
        int sec = Integer.parseInt(ss);

        int curScore = minute*60+sec;
        int highScore = Integer.parseInt(getHighScore());

        if(curScore < highScore)    pushHighScore(scoreName, score);
    }
}
