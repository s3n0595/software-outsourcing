package com.cykj.service.impl;

import com.cykj.bean.*;
import com.cykj.mapper.ChatMapper;
import com.cykj.mapper.DemandMapper;
import com.cykj.service.ChatService;
import com.cykj.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/8/12 6:39
 * @desc:
 */
@Service
public class ChatServiceImpl implements ChatService {
	@Autowired
	private DemandMapper demandMapper;
	@Autowired
	private ChatMapper chatMapper;
	@Override
	public boolean createRoom(TenderRecord tenderRecord) {
		Demand demand = demandMapper.selectDemandById(tenderRecord.getDemandId());
		// 创建新房间
		chatMapper.createRoom(demand.getDemandId());
		// 获取新房间号
		ChatRoom chatRoom = chatMapper.selectLastRoom();
		// 处理成员集合
		List<ChatMember> members = new ArrayList<ChatMember>();
		// 添加雇主
		ChatMember chatMember = new ChatMember();
		chatMember.setChatRoomId(chatRoom.getChatRoomId());
		chatMember.setMemberId(demand.getEmployerId());
		chatMember.setMemberRole("employer");
		members.add(chatMember);
		// 添加服务商
		chatMember = new ChatMember();
		chatMember.setChatRoomId(chatRoom.getChatRoomId());
		chatMember.setMemberId(tenderRecord.getTenderId());
		chatMember.setMemberRole("provider");
		members.add(chatMember);
		// 插入成员列表
		chatMapper.insertMember(members);
		return false;
	}

	@Override
	public Map<String, Object> joinRoom(int demandId) {
		return chatMapper.queryMemberRoomByDemandId(demandId);
	}

	@Override
	public ChatHistory sendMessage(HashMap<String, String> msg) {
		ChatHistory chatHistory = new ChatHistory();
		// 设置房间号
		chatHistory.setChatRoomId(Integer.valueOf(msg.get("chatRoomId").toString()));
		// 设置成员ID
		ChatMember chatMember = new ChatMember();
		chatMember.setMemberRole(msg.get("memberRole"));
		chatMember.setMemberId(Integer.valueOf(msg.get("memberId").toString()));
		chatMember.setChatRoomId(Integer.valueOf(msg.get("chatRoomId").toString()));
		chatHistory.setChatMemberId(chatMapper.queryMemberId(chatMember));
		// 设置消息
		chatHistory.setMessage(msg.get("data"));
		// 设置成员姓名
		chatHistory.setMemberName(msg.get("memberName"));
		// 设置时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		chatHistory.setSendTime(sdf.format(new Date()));
		// 插入数据库
		chatMapper.insertChatHistory(chatHistory);
		return chatHistory;
	}

	@Override
	public List<Map<String, Object>> selectHistoryList(int current, int size, int chatRoomId) {
		return chatMapper.selectHistory(current, size, chatRoomId);
	}
}
