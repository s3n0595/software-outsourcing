package com.cykj.mapper;

import com.cykj.bean.UserInfo;
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
}
