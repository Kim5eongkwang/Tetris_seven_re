package org.example.service;

import java.net.URI;

import lombok.Getter;
import lombok.Setter;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Getter
public class MyWebSocketClient extends WebSocketClient {
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private static final String SENDER="sender";
    private static final String MESSAGE="message";

    private String sender1;
    private String sender2;
    private String action;
    private String roomId;
    private String sessionId;
    private double seed1;
    private double seed2;

    @Setter
    private MultiActionService controller1;
    @Setter
    private MultiActionService controller2;


    public MyWebSocketClient(URI serverUri) {
        super(serverUri);
    }


    @Override
    public void onOpen(ServerHandshake handshakeData) {
        log.info("connected");
    }

    @Override
    public void onMessage(String message) {
        log.info("message in");
        JSONObject obj;
        JSONParser jsonParser= new JSONParser();
        try {
            obj= (JSONObject) jsonParser.parse(message);
            //막 들어갔을때, 받기

            //start
            if(obj.get("type").equals("START")){
                if(obj.get(SENDER).equals("roomId")){
                    roomId=obj.get(MESSAGE).toString();
                    log.info("roomID" +roomId);
                }
                else if(obj.get(SENDER).equals("sessionId")){
                    sessionId=obj.get(MESSAGE).toString();
                    log.info("sessionID"+sessionId);

                }
            }
            //enter
            else if(obj.get("type").equals("ENTER")){
                log.info("Client enter");
                if(obj.get(SENDER).equals("player1")){
                    sender1=obj.get(MESSAGE).toString();
                    log.info("sender1" +sender1);
                }
                else if(obj.get(SENDER).equals("player2")){
                    sender2=obj.get(MESSAGE).toString();
                    log.info("sender2" +sender2);
                }
                else if(obj.get(SENDER).equals("seed")){
                    String splitStrArr[] = obj.get(MESSAGE).toString().split(",");
                     seed1=Double.parseDouble(splitStrArr[0]);
                     seed2=Double.parseDouble(splitStrArr[1]);
                    log.info("seed1 : "+seed1 +" seed2 : "+seed2);
                }
            }
            //game
            else if(obj.get("type").equals("GAME")){
                String inputSessionId=obj.get(SENDER).toString();
                if(!inputSessionId.equals(sender1))
                    log.warn("not equals sender1");
                if(!inputSessionId.equals(sender2))
                    log.warn("not equals sender2");

                try {
                    if(inputSessionId.equals(sender1)){
                        log.info("equals sender1");
                        action=obj.get(MESSAGE).toString();
                        log.info("action : "+action);
                        controller1.action(action);
                        log.info("action : "+action);
                        //작동 호출
                    }else if(inputSessionId.equals(sender2)){
                        action=obj.get(MESSAGE).toString();
                        log.info("action : "+action);
                        controller2.action(action);
                        log.info("action : "+action);
                        //작동 호출
                    }else{
                        log.warn("fail");
                    }
                } catch (CloneNotSupportedException e) {
                   log.error(e.toString());
                }


            }
            //end
            else if(obj.get("type").equals("END")){
                log.info("end game");
                //게임 종료
            }



        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void send(String text) {
        super.send(text);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("connection close");
    }

    @Override
    public void onError(Exception ex) {
        log.error(ex.toString());
    }
}
