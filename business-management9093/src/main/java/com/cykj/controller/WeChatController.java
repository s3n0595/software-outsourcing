package com.cykj.controller;

import com.cykj.bean.CommonResult;
import com.cykj.bean.EmployerAccount;
import com.cykj.bean.ProviderAccount;
import com.cykj.bean.WeChatSession;
import com.cykj.service.EmployerService;
import com.cykj.service.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class WeChatController {
    @Autowired
    WeChatLogin weChatLogin;
    @Resource
    private EmployerService employerService;
    @Resource
    private ProviderService providerService;


    @RequestMapping("wechat/code")
    private ResponseEntity wechatLogin( WeChatSession code) {
        System.out.println(code.getCode());
        ResponseEntity login = weChatLogin.login(code.getCode());

        return login;
    }

    @PostMapping("wechat/login")
    public CommonResult wechatLogin(String phoneNumber) {
        log.info("微信端登入");
        EmployerAccount employerAccount = employerService.queryEmployerIdName(phoneNumber);
        if (employerAccount != null) {
            return new CommonResult(200, "登入成功", employerAccount);
        }
        ProviderAccount providerAccount = providerService.queryProviderIdName(phoneNumber);
        if (providerAccount != null) {
            return new CommonResult(201, "服务商" + providerAccount.getProviderName() + "登陆成功", providerAccount);
        }
        return new CommonResult(400, "用户不存在，请先在网页上注册", null);
    }
}
