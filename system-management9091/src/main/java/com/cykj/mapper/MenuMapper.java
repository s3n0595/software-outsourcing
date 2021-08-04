package com.cykj.mapper;

import com.cykj.bean.Menu;
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
}
