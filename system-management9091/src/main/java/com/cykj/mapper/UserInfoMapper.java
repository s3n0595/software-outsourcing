package com.cykj.mapper;

import com.cykj.bean.Menu;
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
    // 关键词搜索
    List<UserInfo> getSearchUser(@Param("userName") String userName);
    // 用户登录
    UserInfo userInfoLogin(@Param("userAccount") String userAccount,@Param("userPassword") String userPassword);
    // 根据userId删除用户
    int deleteUserList(@Param("userId") int userId);
    // 添加用户
    int addUserInfo(UserInfo userInfo);
    // 修改状态
    int updateState(@Param("userId") int userId,@Param("state") int state);
    // 修改用户信息
    int updateUserInfo(@Param("userId") int userId,@Param("userName") String userName,@Param("roleId") int roleId);
    // 登录后修改用户最后登录时间，登录次数
    int updateLoginDate(@Param("userId") int userId,@Param("loginNumber") int loginNumber,@Param("loginDate") String loginDate);
    //修改用户密码
    int updatePwd(@Param("userId") int userId,@Param("userPassword") String userPassword,@Param("password") String password);
    // 用户账号查重
    UserInfo userAccountExist(@Param("userAccount") String userAccount);

}
