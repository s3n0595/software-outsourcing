package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CapitalFlow {

  private int capitalFlowId;
  private String tradeType;
  private String tradeContent;
  private double tradeCapital;
  private String tradeTime;
  private String phoneNumber;
  private String tradeState;
  private String tradeNo;
}
