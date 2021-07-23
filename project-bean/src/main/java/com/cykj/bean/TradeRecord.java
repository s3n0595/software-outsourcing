package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeRecord {

  private long tradeRecordId;
  private long employerId;
  private long providerId;
  private String tradeTime;
  private String tradePrice;
  private String tradeStatus;



}
