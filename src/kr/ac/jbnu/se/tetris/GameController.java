package src.kr.ac.jbnu.se.tetris;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.kr.ac.jbnu.se.tetris.board.Board;

public class GameController implements ActionListener{
    javax.swing.Timer timer;
    Board board;

    public GameController(int delay, Board board){
        this.board = board;
        timer = new javax.swing.Timer(delay, this);
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

}
