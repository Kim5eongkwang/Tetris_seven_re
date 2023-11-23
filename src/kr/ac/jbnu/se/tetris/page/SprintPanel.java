package src.kr.ac.jbnu.se.tetris.page;

import javax.swing.JPanel;
import src.kr.ac.jbnu.se.tetris.board.SprintBoard;

public class SprintPanel extends GamePanel {
    private SprintBoard board;

    public SprintPanel(){
        super();
        addBoard(415, 10);

    }
    @Override
    public void addBoard(int posX, int posY) {
        board = new SprintBoard(this);
        JPanel boardView = board.getComponent();
        boardView.setBounds(posX, posY, 450, 600);
        frame.add(boardView);
        board.start();
    }

}
