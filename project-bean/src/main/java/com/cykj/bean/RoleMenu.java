package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/5 18:39
 * @desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenu {
    private int rmId;
    private int roleId;
    private int menuId;
    private List<Menu> menuList;
}
