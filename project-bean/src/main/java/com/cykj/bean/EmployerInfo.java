package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerInfo {

  private int employerInfoId;
  private String employerProfile;
  private String credit;
  private String balance;
  private String headPath;
  private int employerId;



}
