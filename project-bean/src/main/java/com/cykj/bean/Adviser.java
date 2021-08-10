package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adviser {

  private int adviserId;
  private int providerId;
  private int serviceTypeId;
  private int auditStatus;
  private DemandType demandType;
  private ProviderInfo providerInfo;
  private ProviderAccount providerAccount;



}
