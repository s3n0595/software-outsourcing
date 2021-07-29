package com.cykj.service.impl;

import com.cykj.bean.UserInfo;
import com.cykj.mapper.UserInfoMapper;
import com.cykj.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/7/28 20:41
 * @desc:
 */
@Service
public class UserInfoServiceImp implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public List<UserInfo> getUserList() {
        return userInfoMapper.getUserList();
    }

    @Override
    public int deleteUserList(int userId) {
        return userInfoMapper.deleteUserList(userId);
    }
}
