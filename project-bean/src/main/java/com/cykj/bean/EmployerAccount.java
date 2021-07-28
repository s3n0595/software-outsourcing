package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployerAccount {

  private int employerId;
  private String employerName;
  private String phoneNumber;
  private String password;
  private EmployerInfo employerInfo;


}
