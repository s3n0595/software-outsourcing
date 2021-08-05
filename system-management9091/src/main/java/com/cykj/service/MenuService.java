package com.cykj.service;

import com.cykj.bean.Menu;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/4 15:07
 * @desc:
 */
public interface MenuService {
    // 获取菜单列表
    List<Menu> getMenuData();
}
