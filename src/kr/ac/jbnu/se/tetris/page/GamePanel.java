package src.kr.ac.jbnu.se.tetris.page;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import src.kr.ac.jbnu.se.tetris.score.Score;

public abstract class GamePanel extends JPanel{
    public JFrame frame;
    private int panelWidth = 1280;
    private int panelHeight = 720;
    private String msg;
    private Score score;

    protected GamePanel(){
        frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(new Dimension(panelWidth, panelHeight));
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
        addBackground();
        return frame;
    }

    public void raiseGameOverFrame(){
        CloseFrame closeFrame = new CloseFrame(getFrame());
        closeFrame.showGameOver();
    }

    public void raiseGameClearFrame() {
        CloseFrame closeFrame = new CloseFrame(getFrame());
        closeFrame.showGameClear(this.msg, score.getScore());
    }



}
