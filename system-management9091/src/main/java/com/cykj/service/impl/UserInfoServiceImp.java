package com.cykj.service.impl;

import com.cykj.bean.Menu;
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
    public List<UserInfo> getSearchUser(String userName) {
        return userInfoMapper.getSearchUser(userName);
    }

    @Override
    public UserInfo userInfoLogin(String userAccount, String userPassword) {
        return userInfoMapper.userInfoLogin(userAccount,userPassword);
    }

    @Override
    public int deleteUserList(int userId) {
        return userInfoMapper.deleteUserList(userId);
    }

    @Override
    public int updatePwd(int userId, String userPassword, String password) {
        return userInfoMapper.updatePwd(userId,userPassword,password);
    }

    @Override
    public int addUserInfo(UserInfo userInfo) {
        return userInfoMapper.addUserInfo(userInfo);
    }

    @Override
    public int updateState(int userId,int state) {
        return userInfoMapper.updateState(userId,state);
    }

    @Override
    public int updateUserInfo(int userId, String userName,int roleId) {
        return userInfoMapper.updateUserInfo(userId,userName,roleId);
    }

    @Override
    public int updateLoginDate(int userId, int loginNumber, String loginDate) {
        return userInfoMapper.updateLoginDate(userId,loginNumber,loginDate);
    }

    @Override
    public UserInfo userAccountExist(String userAccount) {
        return userInfoMapper.userAccountExist(userAccount);
    }
}
