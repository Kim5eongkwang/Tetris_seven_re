package src.kr.ac.jbnu.se.tetris.board;

import src.kr.ac.jbnu.se.tetris.TutorialBlockGenerator;

public class TutorialBoard extends Board{

    public TutorialBoard(){
        setBlockGenerator(new TutorialBlockGenerator());

    }

    @Override
    public void updateTimerLabel(String time) {
        System.out.println("no");
    }
}
