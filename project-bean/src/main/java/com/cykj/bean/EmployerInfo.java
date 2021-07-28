package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerInfo {

  private int employerInfoId;
  private int employerId;
  private String employerProfile;
  private String credit;
  private double balance;
  private String headPath;
  private String email;



}
