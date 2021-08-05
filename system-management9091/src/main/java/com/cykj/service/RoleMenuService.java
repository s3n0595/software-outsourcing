package com.cykj.service;
import com.cykj.bean.RoleMenu;
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
}
