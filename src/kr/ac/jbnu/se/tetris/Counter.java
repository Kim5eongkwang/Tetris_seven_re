package src.kr.ac.jbnu.se.tetris;

import src.kr.ac.jbnu.se.tetris.board.Board;

public class Counter extends GameTimer {

    public Counter(Board board){
        super(board);
    }

    @Override
    public void countUp(int sec) {
        System.out.println("cant count up");
    }
}
