package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adviser {

  private long adviserId;
  private String providerId;
  private long serviceTypeId;
  private String auditStatus;

}
