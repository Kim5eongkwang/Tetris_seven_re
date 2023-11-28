package org.example.timer;


import org.example.board.Board;
import org.example.timer.GameTimer;

public class Counter extends GameTimer {

    public Counter(Board board){
        super(board);
    }

    @Override
    public void countUp(int sec) {
        System.out.println("cant count up");
    }
}
