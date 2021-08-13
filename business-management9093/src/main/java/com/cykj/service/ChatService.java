package com.cykj.service;

import com.cykj.bean.ChatHistory;
import com.cykj.bean.ChatRoom;
import com.cykj.bean.TenderRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/8/12 6:39
 * @desc:
 */
public interface ChatService {
	boolean createRoom(TenderRecord tenderRecord);
	Map<String, Object> joinRoom(int demandId);
	ChatHistory sendMessage(HashMap<String, String> msg);
	List<Map<String, Object>> selectHistoryList(int current, int size, int chatRoomId);
}
