package org.example.board;

import org.example.TutorialBlockGenerator;

public class TutorialBoard extends Board{

    public TutorialBoard(){
        setBlockGenerator(new TutorialBlockGenerator());

    }

    @Override
    public void updateTimerLabel(String time) {
        System.out.println("no");
    }
}
