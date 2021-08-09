package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Works {

  private int worksId;
  private int providerId;
  private String worksTitle;
  private String worksDescribe;
  private String worksAddress;
  private String annexPath;
  private double worksPrice;
  private String releaseTime;
  private int auditStatus;
  private int worksTypeId;
  private DemandType demandType;
  private ProviderAccount providerAccount;

}
