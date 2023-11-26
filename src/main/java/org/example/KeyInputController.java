package org.example;

import java.util.logging.Logger;
import org.example.board.Board;
import org.example.model.KeyInput;


public class KeyInputController {
    Logger logger= Logger.getLogger(KeyInputController.class.getName());
    private final KeyInput input;
    private final Board controller;


    public KeyInputController(KeyInput input, Board controller) {
        logger.info("keyInputController start");
        this.input = input;
        this.controller=controller;
    }

    public void action(int keycode) throws CloneNotSupportedException {
        logger.info("input : " + keycode);
        //if(keycode == input.getPause())
            //controller.pause();
        if (!controller.getIsPaused()) {
            if (keycode == input.getMoveLeft()) {
                controller.moveLeft();
            } else if (keycode == input.getMoveRight()) {
                controller.moveRight();
            } else if (keycode == input.getBlockHold()) {
                controller.holdPiece();
            } else if (keycode == input.getRotateLeft()) {
                controller.rotateLeftCurPiece();
            } else if (keycode == input.getRotateRight()) {
                controller.rotateRightCurPiece();
            } else if (keycode == input.getDropDown()) {
                controller.droppedCurPiece();
            }else if (keycode==input.getOneLineDown()){
                controller.oneLineDownCurPiece();
            }
        }
    }
}




