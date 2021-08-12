package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatHistory {
	private int chatHistoryId;
	private int chatRoomId;
	private int chatMemberId;
	private String message;
	private String sendTime;
	private String memberName;
}
