package com.cykj.service.impl;

import com.cykj.bean.UserInfo;
import com.cykj.mapper.LoginMapper;
import com.cykj.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author guoquansen
 * @date 2021/8/3 11:27 上午
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public int queryAdminByName(UserInfo userInfo) {
        return loginMapper.queryAdminByName(userInfo);
    }
}
