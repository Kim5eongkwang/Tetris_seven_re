package org.example.board;

import javax.swing.JPanel;
import org.example.Counter;
import org.example.RandomBlockGenerator;
import org.example.page.SprintPanel;


import java.awt.*;

public class SprintBoard extends Board {

    private int clearPoint = 40;
    SprintPanel gamePage;

    private transient SprintBoardStatus boardStatus;
    public SprintBoard(SprintPanel parent){
        super();
        setGameTimer(new Counter(this));
        setBlockGenerator(new RandomBlockGenerator());
        boardStatus = new SprintBoardStatus();
        gamePage = parent;
    }

    @Override
    public JPanel getComponent() {
        JPanel ret = new JPanel();
        JPanel superBoard = super.getComponent();
        JPanel status = boardStatus.getStatus();

        ret.setLayout(null);
        ret.setSize(450, 600);
        superBoard.setBounds(0,100,350,500);
        status.setBounds(350,420,60,80);
        ret.add(superBoard);
        ret.add(status);
        ret.setBackground(new Color(0,0,0,0));
        return ret;
    }

    @Override
    public void start() {
        super.start();
        getGameTimer().start();
    }

    @Override
    public void gameOver() {
        super.gameOver();
        gamePage.raiseGameOverFrame();
    }

    @Override
    public void gameClear() {
        super.gameClear();
    }


    public void updateTimerLabel(String time) {
        boardStatus.updateTimeLabel(time);
    }

    @Override
    public int removeFullLines(){
        int removeNum;
        removeNum = super.removeFullLines();
        if(getNumLinesRemoved() >= clearPoint)   gameClear();

        return removeNum;
    }

    @Override
    public void droppedCurPiece() {
        super.droppedCurPiece();
        updateRemovedLines();
    }

    public void updateRemovedLines() {
        int removedLines = getNumLinesRemoved();
        String str = String.format("%d/40", removedLines);
        boardStatus.updateRemovedLineLabel(str);
    }
}
