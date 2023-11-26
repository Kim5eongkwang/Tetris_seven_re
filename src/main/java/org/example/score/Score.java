package org.example.score;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public abstract class Score
{
    String filePath = "src/main/java/org/example/data/highScore.json";
    public String BringScore(String scoreName){
        String highScoreData;
        try {
            Reader reader= new FileReader(filePath);
            JSONParser parser=new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            highScoreData = jsonObject.get(scoreName).toString();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return highScoreData;
    }

    public void pushHighScore(String scoreName, String score){
        try {
            Reader reader = new FileReader(filePath);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            jsonObject.replace(scoreName, score);

            // 파일에 데이터를 다시 쓰기
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(jsonObject.toJSONString());
            }
        } catch (IOException | ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    public abstract String getHighScore();

    public abstract void saveScore(String score);
}
