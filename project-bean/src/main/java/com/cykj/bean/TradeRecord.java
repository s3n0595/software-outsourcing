package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeRecord {

  private int tradeRecordId;
  // 投标记录id
  private int tenderRecordId;
  private String tradeTime;
  private double tradePrice;
  // 状态就分别是 0：预付款、1：等待交付、2：项目交付、3：核验项目、4：交易完成。
  private int tradeStatus;
  private TenderRecord tenderRecord;


}
