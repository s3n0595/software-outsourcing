package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMember {

  private int chatMemberId;
  private int chatRoomId;
  private int memberId;
  private String memberRole;
}
