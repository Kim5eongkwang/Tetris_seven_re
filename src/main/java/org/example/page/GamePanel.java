package org.example.page;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;


import org.example.ComboLetter;
import org.example.board.Board;
import org.example.entity.KeyInput;
import org.example.service.AdapterService;
import org.example.service.KeyInputService;

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
    private transient KeyInput p1key = new KeyInput("src/main/java/org/example/data/player1key.json");
    private transient KeyInput p2key = new KeyInput("src/main/java/org/example/data/player2key.json");
    private JPanel backgroundPanel;

    protected GamePanel(){
        backgroundPanel = new JPanel();

        frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(new Dimension(panelWidth, panelHeight));
        setCloseEvent();
        frame.setLocationRelativeTo(null);
    }
    private void setCloseEvent(){
        getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cleanUp();
                getFrame().dispose();
            }
        });
    }
    public abstract void addBoard(int posX, int posY);
    public int getPanelWidth(){
        return panelWidth;
    }

    public int getPanelHeight(){
        return panelHeight;
    }


    public void addBackground(){
        backgroundPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                ImageIcon backgroundIcon = getGameBackgroundImg();
                Image backgroundImage = backgroundIcon.getImage();

                g.drawImage(backgroundImage, 0 ,0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0,0, panelWidth, panelHeight);
        frame.add(backgroundPanel);
    }

    private ImageIcon getGameBackgroundImg() {
        return new ImageIcon("source/background/gamebackground.png");
    }
    public JPanel getBackgroundPanel(){
        return backgroundPanel;
    }

    public JFrame getFrame(){
        return frame;
    }

    public void raiseGameOverFrame(){
        CloseFrame closeFrame = new CloseFrame(getFrame());
        cleanUp();
        closeFrame.showGameOver();
    }

    public abstract void cleanUp();

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
        AdapterService adapterController = new AdapterService();
        frame.setFocusable(true);
        frame.addKeyListener(adapterController);
        adapterController.addList(new KeyInputService(this.p1key,board));
    }
    public void setAdapter(Board p1Board, Board p2Board) {
        AdapterService adapterController = new AdapterService();
        frame.setFocusable(true);
        frame.addKeyListener(adapterController);
        adapterController.addList(new KeyInputService(this.p1key, p1Board));
        adapterController.addList(new KeyInputService(this.p2key, p2Board));
    }

    private void raiseSingleCombo(){
        ComboLetter letter = new ComboLetter(getFrame());
        letter.setLabel("single");
        addComboLetter(letter);
        Thread thread = new Thread(letter);
        thread.start();
    }

    private void raiseDoubleCombo(){
        ComboLetter letter = new ComboLetter(getFrame());
        letter.setLabel("double");
        addComboLetter(letter);
        Thread thread = new Thread(letter);
        thread.start();
    }

    private void raiseTripleCombo(){
        ComboLetter letter = new ComboLetter(getFrame());
        letter.setLabel("triple");
        addComboLetter(letter);
        Thread thread = new Thread(letter);
        thread.start();
    }

    private void raiseTetrisCombo(){
        ComboLetter letter = new ComboLetter(getFrame());
        letter.setLabel("tetris");
        addComboLetter(letter);
        Thread thread = new Thread(letter);
        thread.start();
    }

    private void addComboLetter(ComboLetter letter){
        letter.addLabel(backgroundPanel, 230, 50);
    }

    public void raiseCombo(int removedLines){
        switch (removedLines){
            case 1 :
                raiseSingleCombo();
                break;
            case 2 :
                raiseDoubleCombo();
                break;
            case 3 :
                raiseTripleCombo();
                break;
            case 4 :
                raiseTetrisCombo();
                break;
            default:
                break;
        }
    }

}
