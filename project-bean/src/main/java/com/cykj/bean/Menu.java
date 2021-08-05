package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

  private int menuId;
  private String menuName;
  private String menuPath;
  private int parentId;
  private String menuIcon;


}
