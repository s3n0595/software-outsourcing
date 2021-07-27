package com.cykj.service;

import com.cykj.bean.ProviderAccount;

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

}
