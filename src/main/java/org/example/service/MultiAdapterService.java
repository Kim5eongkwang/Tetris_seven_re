package org.example.service;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

public class MultiAdapterService extends KeyAdapter {


    Logger logger = Logger.getLogger(MultiAdapterService.class.getName());

    private AbstractInputService multiInputController;

    public MultiAdapterService(){
        logger.info("MultiAdaptController start");
    }
    @Override
    public void keyPressed(KeyEvent e){

        int in= e.getKeyCode();
        in= Character.toLowerCase(in);
        logger.info("pressed : " + in);


            try {
                multiInputController.action(in);
            } catch (CloneNotSupportedException ex) {
                throw new RuntimeException(ex);
            }
        }


        public void addList(AbstractInputService controller){
        this.multiInputController=controller;

    }

}
