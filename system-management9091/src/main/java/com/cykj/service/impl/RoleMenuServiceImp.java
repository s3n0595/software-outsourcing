package com.cykj.service.impl;

import com.cykj.bean.RoleMenu;
import com.cykj.mapper.RoleMenuMapper;
import com.cykj.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/5 18:52
 * @desc:
 */
@Service
public class RoleMenuServiceImp implements RoleMenuService {
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Override
    public  RoleMenu getMenu(int roleId) {
        return roleMenuMapper.getMenu(roleId);
    }
}
