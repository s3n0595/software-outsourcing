package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

  private int roleId;
  private String roleName;
  // 角色描述
  private String roleDescribe;
  // 创建时间
  private String roleDate;

}
