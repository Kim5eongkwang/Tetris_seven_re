package org.example;

public class TutorialBlockGenerator implements BlockGenerator{
    private int blockArrayIndex;
    private static Tetrominoes[] blockArray = {
                    Tetrominoes.MirroredLShape,
                    Tetrominoes.SShape,
                    Tetrominoes.SquareShape,
                    Tetrominoes.ZShape,
                    Tetrominoes.LShape,
                    Tetrominoes.MirroredLShape,
                    Tetrominoes.TShape,
                    Tetrominoes.TShape,
                    Tetrominoes.TShape,
                    Tetrominoes.TShape
    };
    public TutorialBlockGenerator(){
        blockArrayIndex = 0;
    }
    @Override
    public Tetrominoes generateTetrominoes() {
        if(blockArrayIndex == 6)    return blockArray[blockArrayIndex];
        return blockArray[blockArrayIndex++];
    }

    @Override
    public Tetrominoes getFirstNextTetrominoes() {
        return blockArray[blockArrayIndex+1];
    }

    @Override
    public Tetrominoes getSecondNextTetrominoes() {
        return blockArray[blockArrayIndex+2];
    }

    @Override
    public Tetrominoes getThirdnextTetrominoes() {
        return blockArray[blockArrayIndex+3];
    }

    @Override
    public void resetBlockGenerator(){
        blockArrayIndex = 0;
    }
}
