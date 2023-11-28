package org.example.board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.example.Tetrominoes;
import org.example.TutorialBlockGenerator;
import org.example.domian.KeyInput;
import org.example.page.TutorialPanel;

public class TutorialBoard extends Board{
    private TutorialPanel gamePage;
    private Timer stepTimer;
    private int stepIndex;
    public String[] tutorialSteps = new String[6];

    public TutorialBoard(TutorialPanel parent){
        super();
        gamePage = parent;
        stepIndex = 0;
        setBlockGenerator(new TutorialBlockGenerator());
        stepTimer = new Timer(10000, new ActionListener() {  //3000
            @Override
            public void actionPerformed(ActionEvent e) {
                moveToNextStep();
                gamePage.updateTutorialStepLabel();
            }
        });
        setStep();
        setTutorialBoardMap();
        getController().setDelay(1000);
    }

    public void setStep(){
        KeyInput keyInput = new KeyInput("src/main/java/org/example/data/player1key.json");
        Long rotateR = keyInput.getRotateRight();
        Long rotateL = keyInput.getRotateLeft();
        Long moveR = keyInput.getMoveRight();
        Long moveL = keyInput.getMoveLeft();

        tutorialSteps[0] = String.format("<html># Step 1: 테트리스 규칙<br>- 테트리스의 목표는 아래로 떨어지는 블럭을 사용해 가로 한 줄을 완성하는 것입니다.<br>- 한 줄을 채우려고 노력하면서 버티는 게임입니다.<br>- 튜토리얼을 건너뛰려면 스킵 버튼을 눌러주세요</html>");
        tutorialSteps[1] = String.format("<html># Step 2: 블럭 이동하기<br>- 블럭을 좌우로 움직이려면 %c 키(왼쪽)와, %c 키(오른쪽)를 사용하세요.</html>",moveL, moveR);
        tutorialSteps[2] = String.format("<html># Step 3: 블럭 회전하기<br>- 블럭을 회전하려면 %c 키(반시계 방향), %c 키(시계 방향)를 누르세요.</html>",rotateL, rotateR);
        tutorialSteps[3] = String.format("<html># Step 4: 행 제거하기<br>- 블럭을 한 줄에 꽉 차도록 쌓으세요<br>- 가득 찬 줄은 사라지고, 점수를 얻을 수 있습니다.</html>");
        tutorialSteps[4] = String.format("<html># Step 5: SRS(Super Rotation System) 사용하기<br>- SRS는 블럭의 회전을 더 효율적으로 만드는 시스템입니다.<br>- 그림과 같이 블럭을 쌓고, 빈 공간에 도달하면 블럭을 회전시켜 홈 사이에 넣으세요<br>- T모양 블럭을 이용해서 SRS를 수행하고 세 줄을 한 번에 없애보세요</html>");
        tutorialSteps[5] = String.format("<html># 잘하셨습니다!<br>이제 테트리스를 더 재미있게 즐길 수 있을 것입니다.");
    }

    @Override
    public void start() {
        super.start();
        pause();
        stepTimer.start();
    }

    @Override
    public void clearBoard() {
        super.clearBoard();
        setTutorialBoardMap();
    }

    public void resetTutorial(){
        clearBoard();
        getBlockGenerator().resetBlockGenerator();
        newPiece();
    }

    public void skipStep(){
        if(stepIndex == 5)
            return;
        resetStepTimer();
        moveToNextStep();
    }

    @Override
    public int removeFullLines() {
        int ret = super.removeFullLines();

        if(getNumLinesRemoved() >= 12)
            gameClear();

        return ret;
    }

    @Override
    public JPanel getComponent() {
        return super.getComponent();
    }

    @Override
    public void updateTimerLabel(String time) {
        System.out.println("no");
    }

    private void moveToNextStep(){
        if(stepIndex == 4){
            stepTimer.stop();
            pause();
            stepIndex++;
            return;
        }

        stepIndex++;
        resetStepTimer();
    }

    @Override
    public void gameClear() {
        super.gameClear();
        gamePage.raiseGameClearFrame();
    }

    private void resetStepTimer(){
        stepTimer.stop();
        stepTimer.start();
    }

    public String getCurrentStep(){
        return tutorialSteps[stepIndex];
    }

    public void setTutorialBoardMap(){
        for(int posX = 3; posX < BoardWidth; posX++){
            for(int posY = 0; posY < 12; posY++){
                setShapeAt(posX, posY, Tetrominoes.BlackShape);
            }
        }
        setShapeAt(3,11,Tetrominoes.NoShape);
    }
}
