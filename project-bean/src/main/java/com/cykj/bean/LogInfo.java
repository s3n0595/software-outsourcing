package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogInfo {

  private int logId;
  private String operation;
  private String time;
  private int userId;
  private int state;
  private String logDescribe;
  private UserInfo userInfo;



}
