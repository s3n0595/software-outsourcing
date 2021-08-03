package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sms {

  private String accessKeyId;
  private String accessKeySecret;
  private String signName;
  private String templateCode;



}
