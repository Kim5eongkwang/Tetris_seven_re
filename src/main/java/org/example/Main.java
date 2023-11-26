package org.example;

import org.example.page.GamePanel;
import org.example.page.MainPage;
import org.example.page.SprintPanel;
import org.example.page.TimeAttackPanel;

import java.io.IOException;
import javax.swing.JFrame;
import org.example.page.TutorialPanel;
import org.example.page.TwoPlayPanel;


public class  Main {
    public static void main(String[] args) throws IOException {
        MainPage page = new MainPage();
        page.setVisible(true);
    }
}
