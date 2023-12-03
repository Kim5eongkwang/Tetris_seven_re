package org.example.page;

import org.example.RandomBlockGenerator;
import org.example.board.Board;
import org.example.board.TwoPlayBoard;
import org.example.entity.Member;
import org.example.service.*;

import javax.swing.*;
import java.util.Random;

public class SocketPlayPanel extends TwoPlayPanel{

    private TwoPlayBoard player1;
    private TwoPlayBoard player2;
    private int player1RemovedLine = 0;
    private int player2RemovedLine = 0;
    private Member member1;
    private Member member2;
    private Random rand1;
    private Random rand2;
    private String roomId;


    public SocketPlayPanel(){
        super();
        addBoard(100,100);
        addBackground();
    }

    @Override
    public void addBoard(int posX, int posY) {
        setAdapter(player1,player2);
        setPlayer1Board(posX, posY);
        player1.setPlayerName("Player 1");
        setPlayer2Board(posX+700, posY);
        player2.setPlayerName("Player 2");

    }
    private void setPlayer1Board(int posX, int posY){
        player1 = new TwoPlayBoard(this,rand1);
        JPanel boardView = player1.getComponent();
        boardView.setBounds(posX, posY, 450, 500);
        frame.add(boardView);
        player1.start();
    }

    private void setPlayer2Board(int posX, int posY){
        player2 = new TwoPlayBoard(this,rand2);
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
    @Override
    public void cleanUp(){
        player1 = null;
        player2 = null;
    }
    @Override
    public void setAdapter(Board p1board,Board p2board){

        member1= new Member();
        member2= new Member();

        MyWebSocketClient client= WebSocketService.getInstance().getClient();
        roomId=client.getRoomId();

        member1.setSessionId(client.getSender1());
        member2.setSessionId(client.getSender2());
        System.out.println("seed1: "+client.getSeed1()+ "seed2 : " +client.getSeed2());
        Random rand1=new Random((long) client.getSeed1());
        Random rand2=new Random((long) client.getSeed2());

        MultiAdapterService adapterService= new MultiAdapterService();
        frame.setFocusable(true);
        frame.addKeyListener(adapterService);
        adapterService.addList(new MultiInputService(this.p1key,p1board));

        client.setController1(new MultiActionService(p1board));
        client.setController2(new MultiActionService(p2board));
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
