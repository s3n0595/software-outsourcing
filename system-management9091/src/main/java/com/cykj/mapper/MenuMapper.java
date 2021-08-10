package com.cykj.mapper;

import com.cykj.bean.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/4 15:06
 * @desc:
 */
@Repository
public interface MenuMapper {
    // 获取菜单列表
    List<Menu> getMenuData();
    // 添加新菜单
    int addMenu(Menu menu);
    // 菜单查重
    Menu menuNameExist(@Param("menuName") String menuName);
    // 修改菜单信息
    int updateMenu(Menu menu);
    // 删除
    int deleteMenuList(@Param("menuId") int menuId);
}
