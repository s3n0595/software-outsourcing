package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerAccount {

  private int employerId;
  private String phoneNumber;
  private String employerPassword;


}
