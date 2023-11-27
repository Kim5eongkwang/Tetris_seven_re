package org.example.service;


import org.example.board.Board;
import org.example.domian.KeyInput;
import org.example.webSocket.WebSocketService;

public class MultiInputService extends AbstractInputService {


    public MultiInputService(KeyInput input, Board controller) {
        super(input,  controller);
    }

    public void action(int keycode) throws CloneNotSupportedException {
        logger.info("input : " + keycode);

        if(keycode == input.getPause())
            controller.pause();

        if (!controller.getIsPaused()) {

            if (keycode == input.getMoveLeft()) {
                WebSocketService.getInstance().sendMessage("left");
            } else if (keycode == input.getMoveRight()) {
                WebSocketService.getInstance().sendMessage("right");
                //pieceController.moveRight();
            } else if (keycode == input.getBlockHold()) {
                WebSocketService.getInstance().sendMessage("hold");
            } else if (keycode == input.getRotateLeft()) {
                WebSocketService.getInstance().sendMessage("rotateLeft");
            } else if (keycode == input.getRotateRight()) {
                WebSocketService.getInstance().sendMessage("rotateRight");
            } else if (keycode == input.getDropDown()) {
                WebSocketService.getInstance().sendMessage("dropDown");
            }else if (keycode==input.getOneLineDown()){
                WebSocketService.getInstance().sendMessage("oneLineDown");
            }
        }
    }
}



