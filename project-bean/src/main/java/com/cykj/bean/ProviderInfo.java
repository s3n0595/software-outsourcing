package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderInfo {

  private int providerId;
  private String providerName;
  private String providerProfile;
  private String credit;
  private String balance;
  private String headPath;



}
