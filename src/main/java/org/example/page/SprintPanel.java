package org.example.page;

import org.example.board.SprintBoard;

import javax.swing.JPanel;

import org.example.score.SprintScore;
import org.example.service.RankingService;


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
        getFrame().add(boardView);
        board.start();
        setAdapter(board);
    }

    @Override
    public void cleanUp() {
        board.cleanUp();
        board = null;
    }

    @Override
    public void raiseGameClearFrame() {
        String score = board.getCurTime();
        new SprintScore().saveScore(score);
        RankingService.getInstance().saveRanking(RankingService.TIME,score);
        cleanUp();
        setGameClearFrame("Score : " + score);
    }


}
