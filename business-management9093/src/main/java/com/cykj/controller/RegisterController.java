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

/**
 * @author guoquansen
 * @date 2021/7/26 1:32 下午
 */
@Controller
@RequestMapping("/register")
@Slf4j
public class RegisterController {

    @Autowired
    private EmployerService employerService;
    @Autowired
    private ProviderService providerService;

    //雇主注册
    @PostMapping ("/employer")
    @ResponseBody
    public CommonResult addEmployer(EmployerAccount employerAccount){
        log.info("***雇主注册结果***"+employerAccount);
        int i = employerService.queryEmployerAccByTel(employerAccount.getPhoneNumber());
        log.info("***查询号码是否注册***"+i);
        if (i > 0 ) {
            return new CommonResult(444,"雇主账号注册失败",null);
        } else {
            employerService.addUser(employerAccount);
            EmployerAccount queryEmployerIdName = employerService.queryEmployerIdName(employerAccount.getPhoneNumber());
            int addEmployerInfo = employerService.addEmployerInfo(queryEmployerIdName.getEmployerId());
            if (addEmployerInfo > 0) {
                return new CommonResult(200,"雇主信息写入成功",addEmployerInfo);
            } else {
                return new CommonResult(444,"雇主信息写入失败",null);
            }
        }
    }

    //服务商注册
    @PostMapping("/provider")
    @ResponseBody
    public CommonResult addProvider(ProviderAccount providerAccount) {
        log.info("***服务商注册账号信息***"+providerAccount);
        int i = providerService.queryProviderAccByTel(providerAccount.getPhoneNumber());
        log.info("***查询服务商号码是否注册***"+i);
        if (i > 0) {
            return new CommonResult(444,"服务商账号注册失败",null);
        } else {
            providerService.addProvider(providerAccount);
            ProviderAccount queryProviderIdName = providerService.queryProviderIdName(providerAccount.getPhoneNumber());
            int addProviderInfo = providerService.addProviderInfo(queryProviderIdName.getProviderId());
            if (addProviderInfo > 0) {
                return new CommonResult(200,"服务商信息写入成功",addProviderInfo);
            } else {
                return new CommonResult(444,"服务商信息写入失败",null);
            }
        }
    }


}
