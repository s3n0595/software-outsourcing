package com.cykj.controller;

import com.cykj.bean.ChatHistory;
import com.cykj.bean.ChatRoom;
import com.cykj.service.ChatService;
import com.google.gson.Gson;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/8/12 7:35
 * @desc:
 */
@ServerEndpoint(value="/webSocket/{memberRole}/{memberId}/{demandId}")
@Component
public class WebSocket {
	private Gson gson = new Gson();
	//  这里使用静态，让 service 属于类
    private static ChatService chatService;
    // 注入的时候，给类的 service 注入
    @Autowired
    public void setChatService(ChatService chatService) {
        WebSocket.chatService = chatService;
    }
	private static Map<Integer, List<Session>> chatRooms = new HashMap<Integer, List<Session>>();
	/**
     * 建立连接成功调用的方法
     */
	@OnOpen
	public void onOpen(Session session, @PathParam("memberRole") String memberRole, @PathParam("memberId") int memberId, @PathParam("demandId") int demandId) {
		if (memberRole.equals("provider")) {
			System.out.println("服务商已连接");
		} else if (memberRole.equals("employer")) {
			System.out.println("雇主已连接");
		}
		Map<String, Object> chatRoom = chatService.joinRoom(demandId);
		if(!chatRooms.containsKey(chatRoom.get("chatRoomId"))) {
			chatRooms.put((Integer) chatRoom.get("chatRoomId"), new ArrayList<Session>());
		}
		chatRooms.get(chatRoom.get("chatRoomId")).add(session);
		try {
			session.getBasicRemote().sendText("room::" + chatRoom.get("chatRoomId") + "::" + chatRoom.get("demandTitle"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
     *  收到客户端消息调用的方法
     */
	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		HashMap<String, String> msg = gson.fromJson(message,HashMap.class);
		System.out.println("客户端发来消息" + msg);
		switch (msg.get("option")) {
			case "sendMessage":
				updateHistory(chatRooms.get(Integer.valueOf(msg.get("chatRoomId"))), chatService.sendMessage(msg));
				break;
			case "history":
				if(session.isOpen()) {
					List<Map<String, Object>> results = chatService.selectHistoryList(Integer.valueOf(msg.get("current")), 5, Integer.valueOf(msg.get("chatRoomId")));
					session.getBasicRemote().sendText("init::" + gson.toJson(results));
				}
				break;
			case "loadMore":
				if(session.isOpen()) {
					List<Map<String, Object>> results = chatService.selectHistoryList(Integer.valueOf(msg.get("current")), 5, Integer.valueOf(msg.get("chatRoomId")));
					session.getBasicRemote().sendText("loadMore::" + gson.toJson(results));
				}
				break;
		}
	}

	public void updateHistory(List<Session> list, ChatHistory chatHistory) throws IOException {
		List<Map<String, Object>> data = chatService.selectHistoryList(0, 1, chatHistory.getChatRoomId());
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).isOpen()) {
				list.get(i).getBasicRemote().sendText("append::" + gson.toJson(data));
			}
		}
	}




}
