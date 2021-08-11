package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenderRecord {

  private int tenderRecordId;
  private int demandId;
  private int tenderId;
  private String unionStatus;
  private String existTime;
  private double price;
  private String projectTime;
  private String annexPath;

}
