package org.example;

import org.example.page.GamePanel;
import org.example.page.TimeAttackPanel;

import java.io.IOException;
import javax.swing.JFrame;


public class Main {
    public static void main(String[] args) throws IOException {
        GamePanel page = new TimeAttackPanel();
        JFrame frame = page.getFrame();
        frame.setVisible(true);
    }
}
