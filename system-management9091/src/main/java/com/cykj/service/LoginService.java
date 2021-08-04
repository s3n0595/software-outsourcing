package com.cykj.service;

import com.cykj.bean.UserInfo;
import org.springframework.stereotype.Service;

/**
 * @author guoquansen
 * @date 2021/8/3 11:27 上午
 */
public interface LoginService {

    int queryAdminByLog(UserInfo userInfo);

    UserInfo queryUserInfo(UserInfo userInfo);
}
