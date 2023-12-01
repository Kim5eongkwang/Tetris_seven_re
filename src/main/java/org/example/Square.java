package org.example;

import java.awt.image.BufferedImage;
import java.io.IOException;
import org.example.data.Tetrominoes;
import org.example.entity.BlockImg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JPanel;
import org.example.entity.BlockImg;

public abstract class Square extends JPanel {
    public abstract int squareWidth();
    public abstract int squareHeight();
    public void drawImgSquare(Graphics g, int x, int y, Tetrominoes shape) throws IOException {
        BufferedImage bufferedImage = BlockImg.getImage(shape);
        g.drawImage(bufferedImage, x, y, squareWidth(), squareHeight(), null);
    }
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
