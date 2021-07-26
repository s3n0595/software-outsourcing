package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Demand {

  private int demandId;
  private int employerId;
  private int demandStatus;
  private String releaseTime;
  private double predictPrice;
  private String predictTime;
  private String demandTitle;
  private int demandTypeId;
  private String demandDescribe;
  private String annexPath;
  private String predictNumber;


}
