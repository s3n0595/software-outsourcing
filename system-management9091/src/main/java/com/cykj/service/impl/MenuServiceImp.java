package com.cykj.service.impl;

import com.cykj.bean.Menu;
import com.cykj.mapper.MenuMapper;
import com.cykj.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/4 15:07
 * @desc:
 */
@Service
public class MenuServiceImp implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public List<Menu> getMenuData() {
        return menuMapper.getMenuData();
    }

    @Override
    public int addMenu(Menu menu) {
        return menuMapper.addMenu(menu);
    }

    @Override
    public Menu menuNameExist(String menuName) {
        return menuMapper.menuNameExist(menuName);
    }

    @Override
    public int updateMenu(Menu menu) {
        return menuMapper.updateMenu(menu);
    }

    @Override
    public int deleteMenuList(int menuId) {
        return menuMapper.deleteMenuList(menuId);
    }
}
