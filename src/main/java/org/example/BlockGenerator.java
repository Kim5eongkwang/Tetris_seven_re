package org.example;


import org.example.data.Tetrominoes;

public interface BlockGenerator {

    public abstract Tetrominoes generateTetrominoes();
    public abstract Tetrominoes getFirstNextTetrominoes();
    public abstract Tetrominoes getSecondNextTetrominoes();
    public abstract Tetrominoes getThirdnextTetrominoes();
    public abstract void resetBlockGenerator();

}
