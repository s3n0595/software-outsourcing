package com.cykj.service;

import com.cykj.bean.CreditDetails;
import com.cykj.bean.ProviderAccount;

import java.util.Map;

/**
 * @author guoquansen
 * @date 2021/7/27 10:14 下午
 */
public interface ProviderService {

    //增加服务商
    int addProvider(ProviderAccount providerAccount);

    //查询所有注册服务商
    int queryProviderAccByTel(String phoneNumber);

    //查询登陆服务商账号
    int queryProviderAccByLog(ProviderAccount providerAccount);

    //查找注册服务商ID姓名
    ProviderAccount queryProviderIdName(String phoneNum);

    //增加服务商个人信息
    int addProviderInfo(int providerId);

    //增加服务商信息到曝光台
    int addProviderExpose(int providerId);

    Map<String, Object> queryAll();

    //注册信用分
    int addCreditByReg(CreditDetails creditDetails);
}
