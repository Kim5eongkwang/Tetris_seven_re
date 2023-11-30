package org.example.board;

import javax.swing.*;

public class TimeAttackBoardStatus extends BoardStatus {
    private JLabel timeLabel = new JLabel("00:00");
    private JLabel removedLineLabel = new JLabel("0");
    private JLabel dropSpeedLabel = new JLabel();

    public TimeAttackBoardStatus(){
        super();
        setDropSpeedLabel();
        setTimeLabel();
        setRemovedLineLabel();
    }

    private void setDropSpeedLabel(){
        JLabel dropSpeed = new JLabel("Speed");
        setSmallFont(dropSpeed);
        setLargeFont(dropSpeedLabel);
        setLabel(dropSpeed,5, 0);
        setLabel(dropSpeedLabel, 5, dropSpeed.getY()+15);
    }

    private void setTimeLabel(){
        JLabel time = new JLabel("Time");
        setSmallFont(time);
        setLargeFont(timeLabel);
        setLabel(time,5, dropSpeedLabel.getY()+20);
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
    public void updateDropSpeedLabel(String num){
        dropSpeedLabel.setText(num);
    }
}
