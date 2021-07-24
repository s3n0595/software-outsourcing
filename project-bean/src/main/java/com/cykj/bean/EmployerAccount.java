package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerAccount {

  private int employerId;
  private String employerName;
  private String phoneNumber;
  private String password;



}
