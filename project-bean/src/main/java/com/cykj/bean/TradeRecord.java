package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeRecord {

  private int tradeRecordId;
  private int tenderRecordId;
  private String tradeTime;
  private int tradeStatus;


}
