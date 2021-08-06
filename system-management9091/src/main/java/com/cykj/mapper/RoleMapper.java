package com.cykj.mapper;

import com.cykj.bean.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/4 19:02
 * @desc:
 */
@Repository
public interface RoleMapper {
    // 获取角色列表
    List<Role> getRoleList();
    // 关键词搜索
    List<Role> getSearchRole(@Param("roleName") String roleName);
    // 添加新角色
    int addRole(@Param("roleName") String roleName,@Param("roleDescribe") String roleDescribe,@Param("roleDate") String roleDate);
    // 通过roleName查出roleId
    int getRoleId(@Param("roleName") String roleName);
    // 根据roleId删除角色
    int deleteRole(@Param("roleId") int roleId);
}
