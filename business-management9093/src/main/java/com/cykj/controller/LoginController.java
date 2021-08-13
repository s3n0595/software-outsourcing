package com.cykj.controller;

import com.cykj.bean.CommonResult;
import com.cykj.bean.EmployerAccount;
import com.cykj.bean.ProviderAccount;
import com.cykj.service.EmployerService;
import com.cykj.service.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author guoquansen
 * @date 2021/7/24 8:37 下午
 */
@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Resource
    private EmployerService employerService;
    @Resource
    private ProviderService providerService;

    @RequestMapping("/employer")
    @ResponseBody
    public CommonResult employerLogin(EmployerAccount employerAccount){
        log.info("***雇主登陆***");
        int i = employerService.queryEmployerAccByLog(employerAccount);
        if (i > 0) {
            EmployerAccount employerIdName = employerService.queryEmployerIdName(employerAccount.getPhoneNumber());
            return new CommonResult(200,"雇主"+employerAccount.getPhoneNumber()+"登陆成功",employerIdName);
        } else {
            return new CommonResult(400,"雇主账号或密码错误",null);
        }

    }

    @RequestMapping("/provider")
    @ResponseBody
    public CommonResult providerLogin(ProviderAccount providerAccount){
        log.info("***服务商登陆***");
        int i = providerService.queryProviderAccByLog(providerAccount);
        if (i > 0) {
            ProviderAccount providerIdName = providerService.queryProviderIdName(providerAccount.getPhoneNumber());
            return new CommonResult(200,"服务商"+providerAccount.getProviderName()+"登陆成功",providerIdName);
        } else {
            return new CommonResult(400,"服务商账号或密码错误",null);
        }
    }

    //小程序服务商登录
    @RequestMapping("/wechatProvider")
    @ResponseBody
    public CommonResult wechatLogin(String phoneNumber) {
        log.info("微信端登入");
        EmployerAccount employerAccount = employerService.queryEmployerIdName(phoneNumber);
        if (employerAccount != null) {
            return new CommonResult(200, "登入成功", employerAccount);
        }
        ProviderAccount providerAccount = providerService.queryProviderIdName(phoneNumber);
        if (providerAccount != null) {
            return new CommonResult(200, "服务商" + providerAccount.getProviderName() + "登陆成功", providerAccount);
        }
        return new CommonResult(400, "用户不存在，请先在网页上注册", null);
    }

}
