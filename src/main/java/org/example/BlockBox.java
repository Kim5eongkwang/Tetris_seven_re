package org.example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class BlockBox extends Square {
    Tetrominoes box;
    private final int boxSize = 100;
    private final int boxCount = 4;

    public BlockBox(Tetrominoes tetrominoes){
        this.box = tetrominoes;
        setPreferredSize(new Dimension(boxSize, boxSize));
        setOpaque(true);
        setBackground(new Color(66,66,66));
    }

    public void updateBlock(Tetrominoes tetrominoes){
        this.box = tetrominoes;
        repaint();
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        int boxTop = (int) getSize().getHeight() - boxCount * squareHeight();

        for(int i = 0 ; i < 4; i++){
            if(box == Tetrominoes.NoShape)  break;
            int drawX = boxCount / 2 - 1 + Shape.coordsTable[box.ordinal()][i][0];
            int drawY = boxCount - 3 - Shape.coordsTable[box.ordinal()][i][1];
            drawSquare(g, drawX * squareWidth(), boxTop + (boxCount - drawY - 1) * squareHeight(), box);
        }
    }

    @Override
    public int squareWidth() {
        return (int) getSize().getWidth() / boxCount;
    }

    @Override
    public int squareHeight() {
        return (int) getSize().getHeight() / boxCount;
    }

}
