package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import org.example.data.Tetrominoes;


@Getter
@Setter
public class BlockBoxData {
    private Tetrominoes next1 = Tetrominoes.NoShape;
    private Tetrominoes next2 = Tetrominoes.NoShape;
    private Tetrominoes next3 = Tetrominoes.NoShape;
    private Tetrominoes hold = Tetrominoes.NoShape;



}
