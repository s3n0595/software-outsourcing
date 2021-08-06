package com.cykj.service.impl;

import com.cykj.bean.Role;
import com.cykj.mapper.RoleMapper;
import com.cykj.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/4 19:02
 * @desc:
 */
@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> getRoleList() {
        return roleMapper.getRoleList();
    }

    @Override
    public List<Role> getSearchRole(String roleName) {
        return roleMapper.getSearchRole(roleName);
    }

    @Override
    public int addRole(String roleName, String roleDescribe, String roleDate) {
        return roleMapper.addRole(roleName,roleDescribe,roleDate);
    }

    @Override
    public int getRoleId(String roleName) {
        return roleMapper.getRoleId(roleName);
    }

    @Override
    public int deleteRole(int roleId) {
        return roleMapper.deleteRole(roleId);
    }
}
