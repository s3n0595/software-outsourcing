package com.cykj.mapper;

import com.cykj.bean.ProviderAccount;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author guoquansen
 * @date 2021/7/27 10:14 下午
 */
@Mapper
public interface ProviderMapper {

    //增加服务商
    int addProvider(ProviderAccount providerAccount);

    //查询所有注册服务商
    int queryProviderAccByTel(String phoneNumber);

    //查询登陆服务商账号
    int queryProviderAccByLog(ProviderAccount providerAccount);
}
