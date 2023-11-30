package org.example.timer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.example.board.Board;

public abstract class GameTimer implements ActionListener {
    private Timer printTimer = new Timer(1000, this);
    private Board board;
    private boolean isRunning = false;
    private int pausedTime = 0;
    private int oldTime = 0;
    private String timerBuffer;

    public GameTimer(Board board){
        this.board = board;
    }
    public void cleanUp(){
        printTimer = null;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        updateBoardTime();
    }

    abstract public void countUp(int sec);

    public void start(){
        if(!isRunning) {
            pausedTime = (int) System.currentTimeMillis() / 1000 - oldTime;
            isRunning = true;
            printTimer.start();
        }
    }
    public void pause(){
        if(isRunning) {
            oldTime = (int) System.currentTimeMillis() / 1000 - pausedTime;
            isRunning = false;
            printTimer.stop();
        }
    }
    private void upDateTimerBuffer(){
        int secs = getCurrentSec();
        int min;
        int sec;
        sec = secs % 60;
        min = secs / 60 % 60;
        this.timerBuffer = String.format("%02d:%02d", min, sec);
    }

    public int getCurrentSec(){
        if(isRunning)
            return (int) System.currentTimeMillis() / 1000 - pausedTime;
        return oldTime;
    }

    public boolean isTimePassing(){
        return isRunning;
    }

    public int getPausedTime() {
        return pausedTime;
    }

    public Board getBoard(){
        return board;
    }

    public int getOldTime(){
        return oldTime;
    }

    public String getCurTime(){
        upDateTimerBuffer();
        return timerBuffer;
    }
    public void updateBoardTime(){
        board.updateTimerLabel(getCurTime());
    }
}
