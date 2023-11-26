package org.example;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BlockImg {
    static private String filePath = "source/blocks/";
    public static final BufferedImage zShapeImg, sShapeImg, lineShapeImg, tShapeImg, squreShapeImg, lShapeImg, mirroredImg, blackImg;
    static{
        try {
            zShapeImg = ImageIO.read(new File(filePath+"01.png"));
            sShapeImg = ImageIO.read(new File(filePath+"02.png"));
            lineShapeImg = ImageIO.read(new File(filePath+"03.png"));
            tShapeImg = ImageIO.read(new File(filePath+"04.png"));
            squreShapeImg = ImageIO.read(new File(filePath+"05.png"));
            lShapeImg = ImageIO.read(new File(filePath+"06.png"));
            mirroredImg = ImageIO.read(new File(filePath+"07.png"));
            blackImg = ImageIO.read(new File(filePath+"08.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static public BufferedImage getImage(Tetrominoes shape){
        BufferedImage ret;
        switch(shape){
            case ZShape:
                ret = zShapeImg;
                break;
            case SShape:
                ret = sShapeImg;
                break;
            case LineShape:
                ret = lineShapeImg;
                break;
            case TShape:
                ret = tShapeImg;
                break;
            case SquareShape:
                ret = squreShapeImg;
                break;
            case LShape:
                ret = lShapeImg;
                break;
            case MirroredLShape:
                ret = mirroredImg;
                break;
            case BlackShape:
                ret = blackImg;
                break;
            default:
                ret = blackImg;
                break;
        }
        return ret;
    }
}
