package org.example.page;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.example.board.TutorialBoard;

public class TutorialPanel extends GamePanel{
    private TutorialBoard board;
    private JLabel tutorialStepLabel;
    private JButton resetButton;
    private JButton skipButton;

    public TutorialPanel(){
        super();
        addBoard(415,110);
        setTutorialStepLabel();
        setSRSInfoIcon();
        setButton();
        addBackground();
    }
    @Override
    public void addBoard(int posX, int posY) {
        board = new TutorialBoard(this);
        JPanel boardView = board.getComponent();
        boardView.setBounds(posX, posY, 350, 500);
        frame.add(boardView);
        board.start();
    }

    @Override
    public void raiseGameClearFrame() {
        setGameClearFrame("Lets play!");
    }

    private void setTutorialStepLabel(){
        tutorialStepLabel = new JLabel();
        tutorialStepLabel.setFont(new Font("SensSerif", Font.BOLD, 20));
        tutorialStepLabel.setForeground(Color.WHITE);
        tutorialStepLabel.setBounds(900, 150, 350, 300);
        getFrame().add(tutorialStepLabel);
        updateTutorialStepLabel();
    }

    public void updateTutorialStepLabel(){
        String str = board.getCurrentStep();
        tutorialStepLabel.setText(str);
    }

    private void setSRSInfoIcon(){
        ImageIcon imageIcon = new ImageIcon("source/SRSinfo.gif");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(20, 110, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        getFrame().add(imageLabel);
    }

    private void setButton(){
        resetButton = new JButton("다시쌓기");
        skipButton = new JButton("스킵하기");
        skipButton.setBounds(900, 500,120, 50);
        resetButton.setBounds(900, 560, 120, 50);
        getFrame().add(skipButton);
        getFrame().add(resetButton);
        skipButtonAction();
        skipButton.setFocusable(false);
        resetButtonAction();
        resetButton.setFocusable(false);
    }
    public void skipButtonAction(){
        skipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.skipStep();
                updateTutorialStepLabel();

            }
        });
    }
    public void resetButtonAction(){
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.resetTutorial();
            }
        });
    }

}
