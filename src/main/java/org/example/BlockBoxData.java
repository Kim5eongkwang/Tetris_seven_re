package org.example;

import javax.swing.JLabel;

public class BlockBoxData {
    private Tetrominoes next1 = Tetrominoes.NoShape;
    private Tetrominoes next2 = Tetrominoes.NoShape;
    private Tetrominoes next3 = Tetrominoes.NoShape;
    private Tetrominoes hold = Tetrominoes.NoShape;


    public void setNext1(Tetrominoes next1) {
        this.next1 = next1;
    }

    public void setNext2(Tetrominoes next2){
        this.next2 = next2;
    }

    public void setNext3(Tetrominoes next3){
        this.next3 = next3;
    }

    public void setHold(Tetrominoes hold){
        this.hold = hold;
    }

    public Tetrominoes getNext1() {
        return next1;
    }

    public Tetrominoes getNext2() {
        return next2;
    }

    public Tetrominoes getNext3() {
        return next3;
    }

    public Tetrominoes getHold() {
        return hold;
    }

}
