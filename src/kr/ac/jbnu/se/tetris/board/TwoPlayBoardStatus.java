package src.kr.ac.jbnu.se.tetris.board;

import javax.swing.JLabel;
import src.kr.ac.jbnu.se.tetris.BoardStatus;

public class TwoPlayBoardStatus extends BoardStatus {
    private JLabel dropSpeedLabel;
    private JLabel playerLabel;

    public TwoPlayBoardStatus(){
        playerLabel = new JLabel();
        dropSpeedLabel = new JLabel();
        setPlayerLabel();
        setDropSpeedLabel();
    }
    private void setPlayerLabel(){
        setLargeFont(playerLabel);
        setLabel(playerLabel,5, 0);
    }
    private void setDropSpeedLabel(){
        JLabel speed = new JLabel("Speed");
        setSmallFont(speed);
        setLargeFont(dropSpeedLabel);
        setLabel(speed,5, playerLabel.getY()+30);
        setLabel(dropSpeedLabel, 5, speed.getY()+15);
    }

    public void updatePlayerLabel(String time){
        playerLabel.setText(time);
    }

    public void updateDropSpeedLabel(String num){
        dropSpeedLabel.setText(num);
    }
}
