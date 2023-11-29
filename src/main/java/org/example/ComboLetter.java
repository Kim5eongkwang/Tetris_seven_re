package org.example;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class ComboLetter extends JLabel implements Runnable{
    private JFrame repaintFrame;

    public ComboLetter(JFrame repaintFrame){
        setHorizontalAlignment(SwingConstants.CENTER);
        this.repaintFrame = repaintFrame;
        setForeground(Color.white);
        Font font = new Font("Microsoft YaHei", Font.BOLD, 40);
        setFont(font);
    }

    public static void increaseFont(JLabel label){
        Font labelFont = label.getFont();
        label.setFont(new Font(labelFont.getFontName(), labelFont.getStyle(), labelFont.getSize()+1));
    }

    public void setLabel(String comboName){
        switch (comboName){
            case "single" :
                setSingleLabel();
                break;
            case "double" :
                setDoubleLabel();
                break;
            case "triple" :
                setTripleLabel();
                break;
            case "tetris" :
                setTetrisLabel();
                break;
            default:
                break;
        }
    }
    private void setSingleLabel(){
        setText("Single");
    }
    private void setDoubleLabel(){
        setText("Double");
    }
    private void setTripleLabel(){
        setText("Triple");
    }
    private void setTetrisLabel(){
        setText("Tetris!");
    }

    public void scatterLabel() throws InterruptedException {
        setVisible(true);
        for(int i = 10; i >= 0; i--){
            setForeground(new Color(255,255,255,25*i));
            increaseFont(this);
            repaintFrame.repaint();
            Thread.sleep(230);
        }
        setVisible(false);
        repaintFrame.repaint();
    }

    public void addLabel(JPanel panel, int posX, int posY) {
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
            setBounds(posX, posY, 180, 500);
            panel.add(this);
        });
    }

    @Override
    public void run() {
        try {
            scatterLabel();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
