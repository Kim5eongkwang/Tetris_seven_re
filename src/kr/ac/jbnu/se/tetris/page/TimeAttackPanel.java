package src.kr.ac.jbnu.se.tetris.page;

import javax.swing.JPanel;
import src.kr.ac.jbnu.se.tetris.board.TimeAttackBoard;

public class TimeAttackPanel extends GamePanel {
    private TimeAttackBoard board;



    public TimeAttackPanel(){
        super();
        addBoard(415, 10);
    }

    @Override
    public void addBoard(int posX, int posY) {

        board = new TimeAttackBoard(this);
        JPanel boardView = board.getComponent();
        frame.add(boardView);
        boardView.setBounds(posX, posY, 550, 600);
        board.start();
    }
}
