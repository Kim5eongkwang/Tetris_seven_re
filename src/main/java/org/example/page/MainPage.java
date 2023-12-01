package org.example.page;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

import org.example.BackgroundMusic;
import org.example.entity.KeyInput;


public class MainPage extends JFrame {
    static private final int MAINPAGE_SIZE_WIDTH = 1280;
    static private final int MAINPAGE_SIZE_HEIGHT = 720;
    static private transient BackgroundMusic sound;

    private JPanel mainPagePanel;

    private JPanel singlePagePanel;
    private JButton multiPlayBt, closeBt, singlePlayBt ,settingBtn;

    private JPanel multiPlayPagePanel; //뒷 배경을 그린 패널, 메인 패널, 싱글 플레이 패널, 멀티플레이 패널
    private JPanel backgroundPanel;

    public MainPage() throws IOException {

        setSize(new Dimension(MAINPAGE_SIZE_WIDTH, MAINPAGE_SIZE_HEIGHT));
        setLayout(null);
        singlePagePanel = new SinglePlayPage(this);
        singlePagePanel.setVisible(false);
        multiPlayPagePanel = new MutliPlayPage(this);
        multiPlayPagePanel.setVisible(false);
        add(singlePagePanel);
        add(multiPlayPagePanel);

        mainPagePanelInit();
        buttonInit();
        addBackground();


        setTitle("Tetris_seven");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

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

        backgroundPanel.setBounds(0,0, MAINPAGE_SIZE_WIDTH, MAINPAGE_SIZE_HEIGHT);
        mainPagePanel.add(backgroundPanel);
    }

    private ImageIcon getGameBackgroundImg() {
        return new ImageIcon("source/background/mainpagebackground.png");
    }

    /**
     * 버튼의 모양과 위치를 설정하고 mainPAgePanel에 추가하는 메서드
     */
    public void buttonInit(){

        String filePath = "source/button";

        int buttonX = 518;
        int buttonY = 290;
        int buttonWidth = 245;
        int buttonHeight = 70;

        ImageIcon singlePlayIcon = new ImageIcon(filePath+"/singlePlay.png");
        ImageIcon closeIcon = new ImageIcon(filePath+"/close.png");
        ImageIcon multiPlayIcon = new ImageIcon(filePath+"/multiplay.png");
        ImageIcon settingIcon = new ImageIcon(filePath+"/setting.png");

        singlePlayBt = new JButton(singlePlayIcon);
        multiPlayBt = new JButton(multiPlayIcon);
        closeBt = new JButton(closeIcon);
        settingBtn =new JButton(settingIcon);

        singlePlayBt.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        multiPlayBt.setBounds(buttonX, buttonY + buttonHeight + 30, buttonWidth, buttonHeight);
        closeBt.setBounds(buttonX, buttonY +  buttonHeight*2 + 60, buttonWidth, buttonHeight);
        settingBtn.setBounds(buttonX, buttonY +  buttonHeight*3 + 90, buttonWidth, buttonHeight);

        mainPagePanel.add(singlePlayBt);
        mainPagePanel.add(multiPlayBt);
        mainPagePanel.add(closeBt);
        mainPagePanel.add(settingBtn);
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
        settingBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAndShowGUI(MainPage.this);

            }
        });
    }
    private static void createAndShowGUI(JFrame frame) {


        JPanel panel = new JPanel(new FlowLayout());
        panel.setSize(200,1000);

                // 플레이어 선택을 위한 라디오 버튼
        JRadioButton player1RadioButton = new JRadioButton("플레이어 1");
        JRadioButton player2RadioButton = new JRadioButton("플레이어 2");
        ButtonGroup playerGroup = new ButtonGroup();
        playerGroup.add(player1RadioButton);
        playerGroup.add(player2RadioButton);

        JPanel dialogPanel = new JPanel(new GridLayout(0, 2));
        dialogPanel.add(new JLabel("플레이어 선택:"));
        dialogPanel.add(player1RadioButton);
        dialogPanel.add(new JLabel(""));
        dialogPanel.add(player2RadioButton);

        List<JTextField> textFieldList = new ArrayList<>();
        List<JLabel> labelList = new ArrayList<>();
                // 입력 받는 텍스트 필드
        String[] jsonItems = {"rotateR", "rotateL", "moveR", "moveL", "down", "blockHold", "pause", "oneLineDown"};
        for (String item : jsonItems) {
            JLabel label = new JLabel(item);
            JTextField textField = new JTextField(1);
            labelList.add(label);
            textFieldList.add(textField);
            dialogPanel.add(label);
            dialogPanel.add(textField);
        }

                // 다이얼로그 패널 설정


                int result = JOptionPane.showConfirmDialog(frame, dialogPanel, "플레이어 설정", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    if (player1RadioButton.isSelected()) {
                        for (int i = 0; i < labelList.size(); i++){
                            if(textFieldList.get(i).getText()!=null&&!textFieldList.get(i).getText().isEmpty())
                                KeyInput.playerKeySetting(0,labelList.get(i).getText(),textFieldList.get(i).getText().charAt(0));
                        }
                    }
                    else if (player2RadioButton.isSelected()) {
                        for (int i = 0; i < labelList.size(); i++){
                            if(textFieldList.get(i).getText()!=null)
                                KeyInput.playerKeySetting(1,labelList.get(i).getText(),textFieldList.get(i).getText().charAt(0));
                        }
                      }
                    else {
                        JOptionPane.showMessageDialog(frame, "플레이어를 선택하지 않았습니다.");
                    }
                }

        frame.add(panel);

    }
}

