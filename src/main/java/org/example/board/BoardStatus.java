package org.example.board;

import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardStatus {
    private String fontName = "Aharon";
    private JPanel statusPanel = new JPanel();
    private int smallFontSize = 15;
    private int largeFontSize = 20;
    public BoardStatus(){
        statusPanel.setLayout(null);
        statusPanel.setBackground(new Color(66,66,66));
    }

    public String getFontName(){
        return fontName;
    }

    public JPanel getStatus(){
        return statusPanel;
    }

    public void setLabel(JLabel label, int posX, int posY){
        label.setBounds(posX, posY, 100, 20);
        label.setForeground(Color.white);
        statusPanel.add(label);
    }

    public void setSmallFont(JLabel label){
        Font font = new Font(getFontName(), Font.BOLD, smallFontSize);
        label.setFont(font);
    }

    public void setLargeFont(JLabel label){
        Font font = new Font(getFontName(), Font.BOLD, largeFontSize);
        label.setFont(font);
    }

}
