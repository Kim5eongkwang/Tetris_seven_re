package org.example;

import org.example.board.Board;

import java.awt.event.ActionEvent;
import java.util.Objects;


public class ReverseCounter extends GameTimer {

    private int timeLimit = 30;


    public ReverseCounter(Board board){
        super(board);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(Objects.equals(getCurTime(), "00 : 00")){
            getBoard().gameClear();
        }
        updateBoardTime();
    }

    @Override
    public int getCurrentSec(){
        if(isTimePassing())
            return timeLimit - ((int) System.currentTimeMillis() / 1000 - getPausedTime());
        return timeLimit - getOldTime();
    }

    public void countUp(int num) {
        timeLimit += num;
    }
}
