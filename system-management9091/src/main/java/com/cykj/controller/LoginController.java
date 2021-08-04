package com.cykj.controller;

import com.cykj.bean.CommonResult;
import com.cykj.bean.UserInfo;
import com.cykj.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author guoquansen
 * @date 2021/8/3 11:23 上午
 */
@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/admin")
    @ResponseBody
    public CommonResult adminLogin(UserInfo userInfo){
        log.info("***超级管理员登陆***");
        int queryAdminByName = loginService.queryAdminByLog(userInfo);
        if (queryAdminByName > 0) {
            UserInfo info = loginService.queryUserInfo(userInfo);
            log.info("****当前登陆账号的ID"+info.getUserId());
            return new CommonResult(200,"admin登陆成功",info.getUserId());
        } else {
            return new CommonResult(400,"admin登陆失败",null);
        }
    }
}
