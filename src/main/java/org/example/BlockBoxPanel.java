package org.example;

import org.example.data.Tetrominoes;
import org.example.entity.BlockBoxData;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BlockBoxPanel extends JPanel {
    private final int panelWidth = 100;
    private final int panelHeight = 500;
    BlockBox next1 = new BlockBox(Tetrominoes.NoShape);
    BlockBox next2 = new BlockBox(Tetrominoes.NoShape);
    BlockBox next3 = new BlockBox(Tetrominoes.NoShape);
    BlockBox hold = new BlockBox(Tetrominoes.NoShape);

    public BlockBoxPanel(){
        setLayout(null);
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setView();
    }

    public void updateBlockBox(BlockBoxData data){
        next1.updateBlock(data.getNext1());
        next2.updateBlock(data.getNext2());
        next3.updateBlock(data.getNext3());
        hold.updateBlock(data.getHold());
        repaint();
    }

    public void setView(){
        Font font = new Font("Aharon 굵게", Font.BOLD, 15);
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(new Color(66,66,66));

        JLabel holdLabel = new JLabel("HOLD", SwingConstants.CENTER);
        holdLabel.setFont(font);
        JLabel nextLabel = new JLabel("NEXT", SwingConstants.CENTER);
        nextLabel.setFont(font);

        holdLabel.setBounds(0,0,100,20);
        add(holdLabel);

        hold.setBounds(0,25,100,100);
        add(hold);

        nextLabel.setBounds(0,125,100,20);
        add(nextLabel);

        next1.setBounds(0,150,100,100);
        next2.setBounds(0,250,100,100);
        next3.setBounds(0,350,100,100);
        emptyPanel.setBounds(0,450,100,50);

        add(next1);
        add(next2);
        add(next3);
        add(emptyPanel);
    }



}
