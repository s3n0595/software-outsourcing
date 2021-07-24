package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeRecord {

  private int tradeRecordId;
  private int employerId;
  private int providerId;
  private String tradeTime;
  private String tradePrice;
  private String tradeStatus;



}
