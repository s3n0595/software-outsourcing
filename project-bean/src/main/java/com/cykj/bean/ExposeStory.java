package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExposeStory {

  private int exposeStoryId;
  private String exposeStoryTitle;
  private String exposeStoryContent;
  private int auditState;


}
