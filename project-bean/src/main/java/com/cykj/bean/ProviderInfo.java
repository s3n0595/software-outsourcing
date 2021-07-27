package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderInfo {

  private int providerInfoId;
  private int providerId;
  private String providerProfile;
  private String credit;
  private double balance;
  private String headPath;


}
