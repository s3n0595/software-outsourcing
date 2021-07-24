package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adviser {

  private int adviserId;
  private String providerId;
  private int serviceTypeId;
  private String auditStatus;

}
