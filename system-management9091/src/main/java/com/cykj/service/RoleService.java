package com.cykj.service;

import com.cykj.bean.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/4 19:02
 * @desc:
 */
public interface RoleService {
    // 获取角色列表
    List<Role> getRoleList();
    // 关键词搜索
    List<Role> getSearchRole(String roleName);
    // 添加新角色
    int addRole(String roleName,String roleDescribe,String roleDate);
    // 通过roleName查出roleId
    int getRoleId(String roleName);
    // 根据roleId删除角色
    int deleteRole(int roleId);
    // 修改角色信息
    int updateRole(int roleId,String roleName,String roleDescribe);
    // 用户名查重
    Role roleNameExist(String roleName);
}
