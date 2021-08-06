package com.cykj.service;

import com.cykj.bean.Menu;
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
    // 关键词搜索
    List<UserInfo> getSearchUser(String userName);
    // 用户登录
    UserInfo userInfoLogin(String userAccount,String userPassword);
    // 根据userId删除用户
    int deleteUserList(int userId);

    //修改用户密码
    int updatePwd(int userId,String userPassword,String password);

    // 添加用户
    int addUserInfo(UserInfo userInfo);
    //修改状态
    int updateState(int userId,int state);
    // 修改用户信息
    int updateUserInfo(int userId,String userName,int roleId);
    // 登录后修改用户最后登录时间，登录次数
    int updateLoginDate(int userId,int loginNumber,String loginDate);
}
