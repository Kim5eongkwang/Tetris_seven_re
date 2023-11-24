package org.example.board;

import org.example.GameController;
import org.example.RandomBlockGenerator;
import org.example.ReverseCounter;
import org.example.page.TimeAttackPanel;


import javax.swing.*;
import java.awt.*;

public class TimeAttackBoard extends Board {

    private int addTimeLimitConst = 5;
    private int delayreduceConst = 2;
    private TimeAttackBoardStatus boardStatus;
    TimeAttackPanel gamePage;


    public TimeAttackBoard(TimeAttackPanel parent){
        super();
        setTimer(new ReverseCounter(this));
        setBlockGenerator(new RandomBlockGenerator());
        setGameTimer(new ReverseCounter(this));

        boardStatus = new TimeAttackBoardStatus();
        gamePage = parent;
    }

    @Override
    public JPanel getComponent() {
        JPanel ret = new JPanel();
        JPanel superBoard = super.getComponent();
        JPanel status = boardStatus.getStatus();

        ret.setLayout(null);
        ret.setSize(550, 600);
        superBoard.setBounds(0,100,350,500);
        status.setBounds(350,420,80,110);
        ret.add(superBoard);
        ret.add(status);
        ret.setBackground(new Color(0,0,0,0));
        return ret;
    }

    @Override
    public void start() {
        super.start();
        getGameTimer().start();
        updateSpeedLabel();
    }

    @Override
    public void droppedCurPiece() {
        super.droppedCurPiece();
        reduceDelay(delayreduceConst);
        updateRemovedLines();
    }

    private void reduceDelay(int reduceNum){
        GameController controller = getController();
        controller.minusDelay(reduceNum);
        updateSpeedLabel();
    }

    @Override
    public int removeFullLines() {
        int removeNum;
        removeNum = super.removeFullLines();
        if(removeNum > 0){
            plusTimeLimit(removeNum * addTimeLimitConst);
        }
        return removeNum;
    }

    @Override
    public void gameOver() {
        super.gameOver();
        gamePage.raiseGameOverFrame();
    }

    @Override
    public void gameClear() {
        super.gameClear();
    }

    @Override
    public void updateTimerLabel(String time) {
        boardStatus.updateTimeLabel(time);
    }

    void updateRemovedLines(){
        String num = String.format("%d", getNumLinesRemoved());
        boardStatus.updateRemovedLineLabel(num);
    }

    private void plusTimeLimit(int sec){
        getTimer().countUp(sec);
    }

    private void updateSpeedLabel(){
        GameController controller = getController();
        int delay = controller.getDelay();
        Double dropSpeed = 1000.0 / delay;
        String speed = String.format("%.2f/s", dropSpeed);
        boardStatus.updateDropSpeedLabel(speed);
    }
}
