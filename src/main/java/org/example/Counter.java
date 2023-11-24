package org.example;


import org.example.board.Board;

public class Counter extends GameTimer {

    public Counter(Board board){
        super(board);
    }

    @Override
    public void countUp(int sec) {
        System.out.println("cant count up");
    }
}
