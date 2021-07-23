package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Demand {

  private long demandId;
  private long employerId;
  private String auditStatus;
  private String releaseTime;
  private String predictPrice;
  private String predictTime;
  private String demandTitle;
  private long demandTypeId;
  private String demandDescribe;
  private String annexPath;
  private String predictNumber;



}
