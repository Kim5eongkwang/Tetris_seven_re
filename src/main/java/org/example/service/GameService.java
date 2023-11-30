package org.example.service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import org.example.board.Board;


public class GameService implements ActionListener{
    Timer timer;
    Board board;

    public GameService(int delay, Board board){
        this.board = board;
        timer = new javax.swing.Timer(delay, this);
    }

    public void cleanUp(){
        timer = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.actionPerformed(e);
    }

    public void stop(){
        timer.stop();
    }

    public void start(){
        timer.start();
    }

    public int getDelay(){
        return timer.getDelay();
    }

    public void minusDelay(int minusConst){
        int delay = timer.getDelay();
        if(delay <= 10) return;
        timer.setDelay(delay - minusConst);
    }

    public void setDelay(int delay){
        timer.setDelay(delay);
    }

}
