package com.cykj.service;
import com.cykj.bean.RoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/5 18:51
 * @desc:
 */
public interface RoleMenuService {
    // 获取角色id对应的菜单
    RoleMenu getMenu(int roleId);
    // 添加关系
    int addRoleMenu(int roleId, int menuId);
    // 删除roleId对应的menuId关系
    int deleteRoleMenu(int roleId);
}
