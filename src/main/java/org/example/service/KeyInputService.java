package org.example.service;


import org.example.board.Board;
import org.example.entity.KeyInput;

public class KeyInputService extends AbstractInputService {

    public KeyInputService(KeyInput input, Board controller) {
        super(input, controller);


    }

    public void action(int keycode) throws CloneNotSupportedException {
        logger.info("input : " + keycode);
        //if(keycode == input.getPause())
            //controller.pause();
        if (!controller.getIsPaused()) {
            if (keycode == input.getMoveLeftToChar()) {
                controller.moveLeft();
            } else if (keycode == input.getMoveRightToChar()) {
                controller.moveRight();
            } else if (keycode == input.getBlockHold()) {
                controller.holdPiece();
            } else if (keycode == input.getRotateLeftToChar()) {
                controller.rotateLeftCurPiece();
            } else if (keycode == input.RotateRightToChar()) {
                controller.rotateRightCurPiece();
            } else if (keycode == input.getDropDown()) {
                controller.dropDownCurPiece();
            }else if (keycode==input.getOneLineDown()){
                controller.oneLineDownCurPiece();
            }
        }
    }
}




