package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnionInfo {

  private int unionId;
  private String unionName;
  private String allProviderId;
  private String applicationTime;
  private String auditStatus;



}
