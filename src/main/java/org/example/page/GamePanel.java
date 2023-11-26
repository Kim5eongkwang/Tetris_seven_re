package org.example.page;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import org.example.score.Score;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.example.score.TimeAttackScore;


public abstract class GamePanel extends JPanel{
    public JFrame frame;
    private int panelWidth = 1280;
    private int panelHeight = 720;

    protected GamePanel(){
        frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(new Dimension(panelWidth, panelHeight));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

}
