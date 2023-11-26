package org.example.page;

import org.example.board.SprintBoard;

import javax.swing.JPanel;
import org.example.score.SprintScore;


public class SprintPanel extends GamePanel {
    private SprintBoard board;

    public SprintPanel(){
        super();
        addBoard(415, 110);
        drawHighScore(new SprintScore().getHighScore());
        addBackground();
    }
    @Override
    public void addBoard(int posX, int posY) {
        board = new SprintBoard(this);
        JPanel boardView = board.getComponent();
        boardView.setBounds(posX, posY, 450, 600);
        frame.add(boardView);
        board.start();
    }

    @Override
    public void raiseGameClearFrame() {
        String score = board.getCurTime();
        setGameClearFrame("Score : " + score);
    }


}
