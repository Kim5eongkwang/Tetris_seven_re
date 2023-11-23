package src.kr.ac.jbnu.se.tetris;

import java.io.IOException;
import javax.swing.JFrame;
import src.kr.ac.jbnu.se.tetris.page.GamePanel;
import src.kr.ac.jbnu.se.tetris.page.TimeAttackPanel;
import src.kr.ac.jbnu.se.tetris.page.TwoPlayPanel;

public class Main {
    public static void main(String[] args) throws IOException {
        GamePanel page = new TimeAttackPanel();
        JFrame frame = page.getFrame();
        frame.setVisible(true);
    }
}
