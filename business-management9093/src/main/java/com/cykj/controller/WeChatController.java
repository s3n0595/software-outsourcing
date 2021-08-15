package com.cykj.controller;

import com.cykj.bean.*;
import com.cykj.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Slf4j
public class WeChatController {
    @Autowired
    WeChatLogin weChatLogin;
    @Resource
    private EmployerService employerService;
    @Resource
    private ProviderService providerService;
    @Autowired
    private WechatService wechatService;
    @Autowired
    private EmpCenterService empCenterService;
    @Autowired
    private EmpBuyService empBuyService;

    @RequestMapping("wechat/code")
    private ResponseEntity wechatLogin(WeChatSession code) {
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

    @RequestMapping("wechat/recharge")
    public String recharge(int employerId, String balance) {
        System.out.println(employerId + "====" + balance);
        int pri = empCenterService.selempBalance(employerId);
        int price = 0;
        price = pri + Integer.valueOf(balance);
        int i = wechatService.updatebal(employerId, String.valueOf(price));
        System.out.println(i);
        if (i > 0) {
            return "充值成功";
        }
        return "充值失败";
    }

    @RequestMapping("wechat/works")
    public String addWork(TradeWork tradeWork, String balance) {
        int pri = empCenterService.selempBalance(tradeWork.getEmployerId());
        int price = 0;
        price = pri - Integer.valueOf(balance);
        int update = wechatService.updatebal(tradeWork.getEmployerId(), String.valueOf(price));
        if (update > 0) {
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String dateTime = df.format(date); // Formats a Date into a date/time string
            tradeWork.setTradeTime(dateTime);
            int i = empBuyService.wechatWork(tradeWork);
            System.out.println(i);
            if (i > 0) {
                return "购买成功";
            }
        }
        return "购买失败";
    }
}
