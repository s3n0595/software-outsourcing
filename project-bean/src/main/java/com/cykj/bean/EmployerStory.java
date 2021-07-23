package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerStory {

  private long employerStoryId;
  private String employerStoryTitle;
  private String employerStoryContent;


}
