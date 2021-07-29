package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

  private int userId;
  private String userName;
  private String userAccount;
  private String userPassword;
  // 角色id
  private int roleId;
  // 登录次数
  private int loginNumber;
  // 最近一次登录时间
  private String loginDate;
  // 创建者
  private String creator;
  // 状态
  private int state;

}
