package com.cykj.mapper;

import com.cykj.bean.ChatHistory;
import com.cykj.bean.ChatMember;
import com.cykj.bean.ChatRoom;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/8/12 6:53
 * @desc:
 */
@Repository
public interface ChatMapper {
	int createRoom(int demandId);
	ChatRoom selectLastRoom();
	int insertMember(List<ChatMember> members);
	Map<String, Object> queryMemberRoomByDemandId(int demandId);
	int queryMemberId(ChatMember chatMember);
	int insertChatHistory(ChatHistory chatHistory);
	List<Map<String, Object>> selectHistory(@Param("current") int current, @Param("size") int size, @Param("chatRoomId") int chatRoomId);
	int countHistory(int chatRoomId);
}
