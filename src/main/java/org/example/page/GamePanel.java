package org.example.page;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import org.example.AdapterController;
import org.example.KeyInputController;
import org.example.board.Board;
import org.example.model.KeyInput;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public abstract class GamePanel extends JPanel{
    public JFrame frame;
    private int panelWidth = 1280;
    private int panelHeight = 720;
    private KeyInput p1key = new KeyInput("src/main/java/org/example/data/player1key.json");
    private KeyInput p2key = new KeyInput("src/main/java/org/example/data/player2key.json");

    protected GamePanel(){
        frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(new Dimension(panelWidth, panelHeight));
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
    public abstract void addBoard(int posX, int posY);
    public int getPanelWidth(){
        return panelWidth;
    }

    public int getPanelHeight(){
        return panelHeight;
    }


    public void addBackground(){
        JPanel backgroundPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                ImageIcon backgroundIcon = getGameBackgroundImg();
                Image backgroundImage = backgroundIcon.getImage();

                g.drawImage(backgroundImage, 0 ,0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setBounds(0,0, panelWidth, panelHeight);
        frame.add(backgroundPanel);
    }

    private ImageIcon getGameBackgroundImg() {
        return new ImageIcon("source/background/gamebackground.png");
    }

    public JFrame getFrame(){
        return frame;
    }

    public void raiseGameOverFrame(){
        CloseFrame closeFrame = new CloseFrame(getFrame());
        closeFrame.showGameOver();
    }

    public abstract void raiseGameClearFrame();
    public void setGameClearFrame(String msg){
        CloseFrame closeFrame = new CloseFrame(frame);
        closeFrame.showGameClear(msg);
    }

    public void drawHighScore(String score){
        String highscore = "High score : ";
        JLabel highScoreLabel = new JLabel(highscore+score);
        highScoreLabel.setFont(new Font("Microsoft YaHei",Font.BOLD,25));
        highScoreLabel.setForeground(Color.WHITE);
        highScoreLabel.setBounds(0,0,250,40);
        frame.add(highScoreLabel);
    }

    public void setAdapter(Board board){
        AdapterController adapterController = new AdapterController();
        frame.setFocusable(true);
        frame.addKeyListener(adapterController);
        adapterController.addList(new KeyInputController(this.p1key,board));
    }
    public void setAdapter(Board p1Board, Board p2Board){
        AdapterController adapterController = new AdapterController();
        setFocusable(true);
        addKeyListener(adapterController);
        adapterController.addList(new KeyInputController(this.p1key, p1Board));
        adapterController.addList(new KeyInputController(this.p2key, p2Board));
    }

}
