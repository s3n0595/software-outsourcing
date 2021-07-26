package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Works {

  private int worksId;
  private int providerId;
  private String worksDescribe;
  private String annexPath;
  private double worksPrice;
  private String releaseTime;
  private int auditStatus;


}
