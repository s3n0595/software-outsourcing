package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CapitalFlow {

  private int capitalFlowId;
  private String tradeType;
  private String tradeContent;
  private String tradeCapital;
  private String tradeTime;


}
