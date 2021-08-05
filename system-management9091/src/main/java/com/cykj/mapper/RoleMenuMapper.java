package com.cykj.mapper;
import com.cykj.bean.RoleMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/5 18:47
 * @desc:
 */
@Repository
public interface RoleMenuMapper {
    // 获取角色id对应的菜单
    RoleMenu getMenu(@Param("roleId") int roleId);
}
