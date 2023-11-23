package src.kr.ac.jbnu.se.tetris.board;

import javax.swing.JLabel;
import src.kr.ac.jbnu.se.tetris.BoardStatus;

public class SprintBoardStatus extends BoardStatus {
    private JLabel timeLabel = new JLabel("00:00");
    private JLabel removedLineLabel = new JLabel("0/40");

    public SprintBoardStatus(){
        super();
        setTimeLabel();
        setRemovedLineLabel();
    }

    private void setTimeLabel(){
        JLabel time = new JLabel("Time");
        setSmallFont(time);
        setLargeFont(timeLabel);
        setLabel(time,5, 0);
        setLabel(timeLabel, 5, time.getY()+15);
    }
    private void setRemovedLineLabel(){
        JLabel line = new JLabel("Line");
        setSmallFont(line);
        setLargeFont(removedLineLabel);
        setLabel(line,5, timeLabel.getY()+20);
        setLabel(removedLineLabel, 5, line.getY()+15);
    }

    public void updateTimeLabel(String time){
        timeLabel.setText(time);
    }

    public void updateRemovedLineLabel(String num){
        removedLineLabel.setText(num);
    }
}
