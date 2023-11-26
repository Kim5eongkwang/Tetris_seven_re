package org.example;

import java.util.logging.Logger;
import org.example.board.Board;

public class MultiActionController {
    Logger logger = Logger.getLogger(KeyInputController.class.getName());

    private final Board controller;

    public MultiActionController(Board controller) {
        logger.info("MultiActionController start");

        this.controller = controller;
    }

    public void action(String message) throws CloneNotSupportedException {
        logger.info("input : " + message);

        //if (message.equals("pause"))
            //controller.pause();

        if (!controller.getIsPaused()) {

            if (message.equals("left")) {
                controller.moveLeft();
            } else if (message.equals("right")) {
                controller.moveRight();
            } else if (message.equals("hold")) {
                controller.holdPiece();
            } else if (message.equals("rotateLeft")) {
                controller.rotateLeftCurPiece();
            } else if (message.equals("rotateRight")) {
                controller.rotateRightCurPiece();
            } else if (message.equals("dropDown")) {
                controller.dropDownCurPiece();
            } else if (message.equals("oneLineDown")) {
                controller.oneLineDownCurPiece();
            }
        }
    }
}

