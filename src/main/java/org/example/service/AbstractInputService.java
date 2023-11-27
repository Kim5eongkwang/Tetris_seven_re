package org.example.service;


import org.example.board.Board;
import org.example.domian.KeyInput;

import java.util.logging.Logger;

public abstract class AbstractInputService {

    Logger logger= Logger.getLogger(KeyInputService.class.getName());
    protected KeyInput input;
    protected Board controller;

    public AbstractInputService(KeyInput input, Board controller) {
        logger.info("InputController start");
        this.input = input;
        this.controller=controller;

    }
    public abstract void action(int keycode)throws CloneNotSupportedException ;


}
