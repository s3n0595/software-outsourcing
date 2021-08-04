package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
  private DemandType demandType;

}
