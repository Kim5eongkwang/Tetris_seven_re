package src.kr.ac.jbnu.se.tetris;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomBlockGenerator implements BlockGenerator{
    private Random rand;
    private int tetrominoesSize = 7;
    private int queueIndex = 0;
    private int queueSize = tetrominoesSize * 2;
    private Tetrominoes[] blockQueue = new Tetrominoes[queueSize];
    private int seed;

    public RandomBlockGenerator(){
        setRandom();
        updateSeed();
        Tetrominoes[] queue = getHalfQueue();
        System.arraycopy(queue, 0, blockQueue, 0, queueSize/2);
    }

    public RandomBlockGenerator(Random rand){
        setRandom(rand);
        updateSeed();
        Tetrominoes[] queue = getHalfQueue();
        System.arraycopy(queue, 0, blockQueue, 0, queueSize/2);
    }

    private void setRandom(){
        double x= Math.random()*10000;
        rand= new Random((long) x);
        rand.nextInt();
    }

    private void setRandom(Random rand){
        this.rand = rand;
        rand.nextInt();
    }

    private void updateSeed(){
        this.seed = createUnsignedRandomNum();
    }
    private int createUnsignedRandomNum(){
        int randomNum = rand.nextInt();
        if(randomNum < 0)
            return -randomNum;
        return randomNum;
    }

    private void updateQueue(){
        if(queueIndex == 0) {
            Tetrominoes[] queue = getHalfQueue();
            System.arraycopy(queue, 0, blockQueue, queueSize/2, queueSize/2);
        }else if(queueIndex == queueSize / 2){
            Tetrominoes[] queue = getHalfQueue();
            System.arraycopy(queue, 0, blockQueue, 0, queueSize/2);
        }
    }

    @Override
    public Tetrominoes generateTetrominoes() {
        updateQueue();
        Tetrominoes ret = blockQueue[queueIndex];
        queueIndex += 1;
        queueIndex %= queueSize;
        return ret;
    }

    @Override
    public Tetrominoes getFirstNextTetrominoes() {
        int index = queueIndex;
        return blockQueue[index];
    }

    @Override
    public Tetrominoes getSecondNextTetrominoes() {
        int index = queueIndex + 1;
        index %= queueSize;
        return blockQueue[index];
    }

    @Override
    public Tetrominoes getThirdnextTetrominoes() {
        int index = queueIndex + 2;
        index %= queueSize;
        return blockQueue[index];
    }

    private Tetrominoes[] getHalfQueue(){
        Tetrominoes[] array = new Tetrominoes[tetrominoesSize];
        List<Integer> numlist= new ArrayList<>();
        for(int i = 1; i <= tetrominoesSize; i++){
            numlist.add(i);
        }
        Collections.shuffle(numlist, new Random(seed));
        for(int i = 0; i < tetrominoesSize; i++){
            array[i] = Tetrominoes.values()[numlist.get(i)];
        }
        updateSeed();
        return array;
    }
}
