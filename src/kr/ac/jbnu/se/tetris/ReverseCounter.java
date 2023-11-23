package src.kr.ac.jbnu.se.tetris;

import java.awt.event.ActionEvent;
import java.util.Objects;
import src.kr.ac.jbnu.se.tetris.board.Board;

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
