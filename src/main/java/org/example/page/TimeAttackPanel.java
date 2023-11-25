package org.example.page;

import org.example.board.TimeAttackBoard;

import javax.swing.JPanel;


public class TimeAttackPanel extends GamePanel {
    private TimeAttackBoard board;



    public TimeAttackPanel(){
        super();
        addBoard(415, 10);
        addBackground();
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
