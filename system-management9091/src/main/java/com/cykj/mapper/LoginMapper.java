package com.cykj.mapper;

import com.cykj.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author guoquansen
 * @date 2021/8/3 11:23 上午
 */
@Mapper
public interface LoginMapper {

    int queryAdminByLog(UserInfo userInfo);

    //查询用户信息
    UserInfo queryUserInfo(UserInfo userInfo);
}
