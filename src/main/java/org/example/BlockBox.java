package org.example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class BlockBox extends JPanel implements Square {
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

    @Override
    public void drawSquare(Graphics g, int x, int y, Tetrominoes shape) {
        Color[] colors = { new Color(0, 0, 0), new Color(204, 102, 102), new Color(102, 204, 102),
                new Color(102, 102, 204), new Color(204, 204, 102), new Color(204, 102, 204), new Color(102, 204, 204),
                new Color(218, 170, 0) };

        Color color = colors[shape.ordinal()];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight() - 1, x + squareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1, x + squareWidth() - 1, y + 1);
    }

}
