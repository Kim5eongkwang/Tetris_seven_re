package org.example.page;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.example.BackgroundMusic;


public class MainPage extends JFrame {
    static private final int MAINPAGE_SIZE_WIDTH = 1280;
    static private final int MAINPAGE_SIZE_HEIGHT = 720;
    BackgroundMusic sound;
    JPanel backgroundPanel;

    static JPanel mainPagePanel;

    JPanel singlePagePanel;
    JButton multiPlayBt, closeBt, singlePlayBt;

    JPanel multiPlayPagePanel; //뒷 배경을 그린 패널, 메인 패널, 싱글 플레이 패널, 멀티플레이 패널

    public MainPage() throws IOException {


        setSize(new Dimension(MAINPAGE_SIZE_WIDTH, MAINPAGE_SIZE_HEIGHT));
        setLayout(null);
        singlePagePanel = new SinglePlayPage(this);
        multiPlayPagePanel = new MutliPlayPage(this);
        add(singlePagePanel);
        add(multiPlayPagePanel);

        mainPagePanelInit();
        buttonInit();

        add(backgroundPanel);
        backgroundPanel.setSize(MAINPAGE_SIZE_WIDTH,MAINPAGE_SIZE_HEIGHT);

        setTitle("Tetris_seven");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        sound = new BackgroundMusic();
        sound.mainManuPlay();
    }

    public JPanel getMainPagePanel(){
        return mainPagePanel;
    }

    /**
     * 버튼이 들어갈 mainPagePanel을 초기화하고 프레임에 추가하는 메서드
     */
    public void mainPagePanelInit(){
        mainPagePanel = new JPanel();
        mainPagePanel.setLayout(null);
        mainPagePanel.setBackground(new Color(0,0,0,0));
        mainPagePanel.setSize(1280, 720);
        mainPagePanel.setBounds(0,0,1280,720);
        add(mainPagePanel);
    }

    /**
     * 버튼의 모양과 위치를 설정하고 mainPAgePanel에 추가하는 메서드
     */
    public void buttonInit(){

        String filePath = "source/button";

        int buttonX = 518;
        int buttonY = 70;
        int buttonWidth = 245;
        int buttonHeight = 70;

        ImageIcon singlePlayIcon = new ImageIcon(filePath+"singlePlay.png");
        ImageIcon closeIcon = new ImageIcon(filePath+"close.png");
        ImageIcon multiPlayIcon = new ImageIcon(filePath+"multiplay.png");

        singlePlayBt = new JButton(singlePlayIcon);
        multiPlayBt = new JButton(multiPlayIcon);
        closeBt = new JButton(closeIcon);

        singlePlayBt.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        multiPlayBt.setBounds(buttonX, buttonY + buttonHeight + 30, buttonWidth, buttonHeight);
        closeBt.setBounds(buttonX, buttonY +  buttonHeight*2 + 60, buttonWidth, buttonHeight);

        mainPagePanel.add(singlePlayBt);
        mainPagePanel.add(multiPlayBt);
        mainPagePanel.add(closeBt);
        buttonAction();
    }

    /**
     * 버튼을 눌렀을 때 실행되는 action을 설정힌 메서드
     */
    public void buttonAction(){
        singlePlayBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPagePanel.setVisible(false);
                singlePagePanel.setVisible(true);
            }
        });
        multiPlayBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPagePanel.setVisible(false);
                multiPlayPagePanel.setVisible(true);
            }
        });
        closeBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

}
