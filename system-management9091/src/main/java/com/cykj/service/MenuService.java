package com.cykj.service;

import com.cykj.bean.Menu;
import org.apache.ibatis.annotations.Param;

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
    // 添加新菜单
    int addMenu(Menu menu);
    // 菜单查重
    Menu menuNameExist(String menuName);
    // 修改菜单信息
    int updateMenu(Menu menu);
    // 删除
    int deleteMenuList(int menuId);
}
