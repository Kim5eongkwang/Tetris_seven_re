package org.example.page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseFrame extends JFrame {
    private JFrame closeFrame;
    private JFrame gameFrame;
    private JButton closeButton;
    private int frameWidth = 500;
    private int frameHeight = 300;

    public CloseFrame(JFrame parent){
        closeFrame = new JFrame("close frame");
        closeFrame.setLayout(null);
        closeFrame.setPreferredSize(new Dimension(frameWidth, frameHeight));
        closeButton = new JButton("확인");
        gameFrame = parent;
        closeFrame.setLocationRelativeTo(null);
    }

    public void showGameClear(String msg){
        JLabel msgLabel = new JLabel(msg, SwingConstants.CENTER);
        Font font = new Font("Aharon", Font.BOLD, 25);
        msgLabel.setFont(font);

        msgLabel.setBounds(0,0,500,100);
        closeButton.setBounds(200,200,100,50);

        closeFrame.add(msgLabel);
        closeFrame.add(closeButton);

        buttonAction();

        closeFrame.setVisible(true);
        closeFrame.pack();

    }

    public void showGameOver(){
        setGameOver();
        closeFrame.pack();
    }

    private void setGameOver(){
        JLabel msgLabel = new JLabel("GameOver",SwingConstants.CENTER);
        Font font = new Font("Aharon", Font.BOLD, 25);
        msgLabel.setFont(font);

        msgLabel.setBounds(0,0,500,100);
        closeButton.setBounds(200,200,100,50);

        closeFrame.add(msgLabel);
        closeFrame.add(closeButton);
        buttonAction();

        closeFrame.setVisible(true);
    }

    private void buttonAction(){
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.dispose();
                closeFrame.dispose();
            }
        });
    }
}
