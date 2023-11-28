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
    private int frameHeight = 400;

    public CloseFrame(JFrame parent){
        closeFrame = new JFrame("close frame");
        closeFrame.setLayout(null);
        closeFrame.setPreferredSize(new Dimension(frameWidth, frameHeight));
        closeButton = new JButton("확인");
        gameFrame = parent;
    }

    public void showGameClear(String msg){
        JLabel msgLabel = new JLabel(msg, SwingConstants.CENTER);
        Font font = new Font("Aharon", Font.BOLD, 25);
        msgLabel.setFont(font);

        msgLabel.setBounds(0,0,400,25);
        closeButton.setBounds(200,300,200,100);

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
        JLabel msgLabel = new JLabel("GameOver");
        Font font = new Font("Aharon", Font.BOLD, 25);
        msgLabel.setFont(font);

        msgLabel.setBounds(0,0,400,100);
        closeButton.setBounds(200,300,200,100);

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
