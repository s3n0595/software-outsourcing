package com.cykj.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/8/9 20:27
 * @desc:
 */
@ServerEndpoint(value="/webSocket/{user}/{userID}")
@Component
public class WebSocketController {

    private static Map<String, Session> proSession = new HashMap<String, Session>();
    private static Map<String, Session> empSession = new HashMap<String, Session>();

    private Gson gson = new Gson();
	/**
     * 建立连接成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("user") String user, @PathParam("userID") String userID) throws IOException {
        if(user.equals("provider")) {
            proSession.put(userID, session);
            System.out.println("服务商已连接");
        } else if(user.equals("employer")){
            empSession.put(userID, session);
            System.out.println("雇主已连接");
        }
    }

    /**
     * 关闭连接调用的方法
     */
    @OnClose
    public void onClose(Session session){
	    for(String key : empSession.keySet()) {
            if (empSession.get(key) == session) {
                System.out.println("雇主离开");
                break;
            }
        }
        for(String key : proSession.keySet()) {
            if (proSession.get(key) == session) {
                System.out.println("服务商离开");
                break;
            }
        }
    }
	@OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }
    /**
     *  收到客户端消息调用的方法
     */
    @OnMessage
    public void onMessage(String message) throws IOException {
        Map<String, String> msg = gson.fromJson(message,HashMap.class);
        if(msg.get("senderRole").equals("provider")) {
            // 收到服务商发来的消息
            System.out.println("服务商发来消息" + msg);
            switch (msg.get("option")) {
	            case "test":
		            System.out.println(msg.get("msg"));
                default:
                    break;
            }
        } else if(msg.get("senderRole").equals("employer")){
            // 收到雇主发来的消息
            System.out.println("雇主发来消息" + msg);
            switch (msg.get("option")) {

                default:
                    break;
            }
        }
    }
    public static void sendMessage(String sessionMap, String receiverID, String text) throws IOException {
        Map<String, Session> sendMap = null;
        System.out.println("send ->" + sessionMap);
        if(sessionMap.equals("proSession")) {
           sendMap = proSession;
        } else if(sessionMap.equals("empSession")){
            sendMap = empSession;
        }
        if(receiverID.equals("all")) {
            for(String key : sendMap.keySet()) {
                if(sendMap.get(key).isOpen()){
                    sendMap.get(key).getBasicRemote().sendText(text);
                }
            }
        } else {
            String[] receiverIDs = receiverID.split("::");
            for(int i = 0; i < receiverIDs.length; i++) {
                if(sendMap.get(receiverIDs[i]) != null && sendMap.get(receiverIDs[i]).isOpen()) {
                   sendMap.get(receiverIDs[i]).getBasicRemote().sendText(text);
                }
            }
        }
    }
}
