package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenderRecord {

  private long tenderRecordId;
  private long demandId;
  private long tenderId;
  private String unionStatus;
  private String existTime;
  private String price;
  private String projectTime;



}
