package org.example.page;

import org.example.board.TwoPlayBoard;
import javax.swing.JPanel;
import org.example.model.KeyInput;

public class TwoPlayPanel extends GamePanel{

    private TwoPlayBoard player1;
    private TwoPlayBoard player2;
    private int player1RemovedLine = 0;
    private int player2RemovedLine = 0;
    public TwoPlayPanel(KeyInput p1key, KeyInput p2key){
        super(p1key);
        addBoard(100,100);
        addBackground();
    }
    @Override
    public void addBoard(int posX, int posY) {
        setPlayer1Board(posX, posY);
        player1.setPlayerName("Player 1");
        setPlayer2Board(posX+700, posY);
        player2.setPlayerName("Player 2");
    }

    private void setPlayer1Board(int posX, int posY){
        player1 = new TwoPlayBoard(this);
        JPanel boardView = player1.getComponent();
        boardView.setBounds(posX, posY, 450, 500);
        frame.add(boardView);
        player1.start();
    }

    private void setPlayer2Board(int posX, int posY){
        player2= new TwoPlayBoard(this);
        player2 = new TwoPlayBoard(this);
        JPanel boardView = player2.getComponent();
        boardView.setBounds(posX, posY, 450, 500);
        frame.add(boardView);
        player2.start();
    }

    public void updateDropSpeed(){
        updateNumLineRemoved();
    }

    private void updateNumLineRemoved(){
        int curPlayer1LineRemoved = player1.getNumLinesRemoved();
        int curPlayer2LineRemoved = player2.getNumLinesRemoved();

        speedUpPlayer1(curPlayer2LineRemoved - player2RemovedLine);
        player1.updateSpeedLabel();
        speedUpPlayer2(curPlayer1LineRemoved - player1RemovedLine);
        player2.updateSpeedLabel();

        this.player1RemovedLine = curPlayer1LineRemoved;
        this.player2RemovedLine = curPlayer2LineRemoved;
    }

    private void speedUpPlayer1(int num){
        if(num < 0) return;
        player1.reduceDelay(num);
    }

    private void speedUpPlayer2(int num){
        if(num < 0) return;
        player2.reduceDelay(num);
    }

    private void cleanUp(){
        player1 = null;
        player2 = null;
    }

    @Override
    public void raiseGameClearFrame(){
        if(!player1.getIsStarted()) {
            player2.pause();
            cleanUp();
            setGameClearFrame("Winner is player2");
            return;
        }
        player1.pause();
        cleanUp();
        setGameClearFrame("Winner is player1");
    }

}
