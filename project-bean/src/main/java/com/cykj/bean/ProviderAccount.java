package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderAccount {

  private long providerId;
  private String providerName;
  private String phoneNumber;
  private String password;



}
