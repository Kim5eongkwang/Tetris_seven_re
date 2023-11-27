package org.example.service;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Logger;

public class AdapterService extends KeyAdapter {
    Logger logger = Logger.getLogger(AdapterService.class.getName());
    private final ArrayList<AbstractInputService> memberList= new ArrayList<>();


    public AdapterService(){
        logger.info("adaptController start");
    }
    @Override
    public void keyPressed(KeyEvent e){
        int in= e.getKeyCode();
        in= Character.toLowerCase(in);
        logger.info("pressed : "+in);
        for (AbstractInputService abstractInputService : memberList) {
            try {
                abstractInputService.action(in);
            } catch (CloneNotSupportedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }


    public void addList(AbstractInputService controller){
        memberList.add(controller);
    }

}
