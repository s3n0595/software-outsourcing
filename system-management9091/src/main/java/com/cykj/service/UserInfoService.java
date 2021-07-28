package com.cykj.service;

import com.cykj.bean.UserInfo;

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
}
