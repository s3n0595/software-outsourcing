package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenderRecord {

  private int tenderRecordId;
  private int demandId;
  // 投标人Id
  private int tenderId;
  // 联盟状态 0：个人 其他为联盟id
  private String unionStatus;
  private String existTime;
  private double price;
  // 工期
  private String projectTime;
  private Demand demand;


}
