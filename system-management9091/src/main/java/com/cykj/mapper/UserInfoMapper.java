package com.cykj.mapper;

import com.cykj.bean.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/7/28 20:37
 * @desc:
 */
@Repository
public interface UserInfoMapper {
    // 获取用户列表
    List<UserInfo> getUserList();
    // 根据userId删除用户
    int deleteUserList(@Param("userId") int userId);
}
