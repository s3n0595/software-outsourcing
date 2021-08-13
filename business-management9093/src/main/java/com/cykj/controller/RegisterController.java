package com.cykj.controller;

import com.cykj.bean.CommonResult;
import com.cykj.bean.CreditDetails;
import com.cykj.bean.EmployerAccount;
import com.cykj.bean.ProviderAccount;
import com.cykj.service.EmployerService;
import com.cykj.service.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author guoquansen
 * @date 2021/7/26 1:32 下午
 */
@Controller
@RequestMapping("/register")
@Slf4j
@CrossOrigin
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
            return new CommonResult(400,"雇主账号注册失败",null);
        } else {
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String dateTime = df.format(date); // Formats a Date into a date/time string.
            employerAccount.setRegTime(dateTime);
            employerService.addUser(employerAccount);
            EmployerAccount account = employerService.queryEmployerInfo(employerAccount.getPhoneNumber());
            int addEmployerInfo = employerService.addEmployerInfo(account.getEmployerId());
            int addEmployerStory = employerService.addEmployerStory(account.getEmployerId());
            int addEmployerExpose = employerService.addEmployerExpose(account.getEmployerId());
            CreditDetails creditDetails = new CreditDetails();
            creditDetails.setRemarks("注册账号");
            creditDetails.setScore("+80");
            creditDetails.setUserId(account.getEmployerId());
            creditDetails.setCreditTime(account.getRegTime());
            creditDetails.setType(account.getRole());
            employerService.addCreditByReg(creditDetails);
            if (addEmployerInfo > 0 && addEmployerStory > 0 && addEmployerExpose > 0) {
                return new CommonResult(200,"雇主信息写入成功",addEmployerInfo);
            } else {
                return new CommonResult(400,"雇主信息写入失败",null);
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
            return new CommonResult(400,"服务商账号注册失败",null);
        } else {
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String dateTime = df.format(date); // Formats a Date into a date/time string.
            providerAccount.setRegTime(dateTime);
            providerService.addProvider(providerAccount);
            ProviderAccount queryProviderIdName = providerService.queryProviderIdName(providerAccount.getPhoneNumber());
            int addProviderInfo = providerService.addProviderInfo(queryProviderIdName.getProviderId());
            int addProviderExpose = providerService.addProviderExpose(queryProviderIdName.getProviderId());
            CreditDetails creditDetails = new CreditDetails();
            creditDetails.setRemarks("注册账号");
            creditDetails.setScore("+80");
            creditDetails.setUserId(queryProviderIdName.getProviderId());
            creditDetails.setCreditTime(queryProviderIdName.getRegTime());
            creditDetails.setType(queryProviderIdName.getRole());
            providerService.addCreditByReg(creditDetails);
            if (addProviderInfo > 0 && addProviderExpose > 0) {
                return new CommonResult(200,"服务商信息写入成功",addProviderInfo);
            } else {
                return new CommonResult(400,"服务商信息写入失败",null);
            }
        }
    }


}
