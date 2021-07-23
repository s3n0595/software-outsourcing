package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Works {

  private long worksId;
  private long providerId;
  private String worksDescribe;
  private String annexPath;
  private String worksPrice;
  private String releaseTime;
  private String auditStatus;


}
