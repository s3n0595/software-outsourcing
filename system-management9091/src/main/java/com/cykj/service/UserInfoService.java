package com.cykj.service;

import com.cykj.bean.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/7/28 20:40
 * @desc:
 */
public interface UserInfoService {
    // 获取用户列表
    List<UserInfo> getUserList();

    // 根据userId删除用户
    int deleteUserList(int userId);
}
