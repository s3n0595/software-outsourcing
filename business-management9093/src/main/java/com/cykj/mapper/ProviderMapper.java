package com.cykj.mapper;

import com.cykj.bean.EmployerAccount;
import com.cykj.bean.ProviderAccount;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.cykj.bean.ProviderInfo;
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

    //查找注册服务商ID姓名
    ProviderAccount queryProviderIdName(String phoneNum);

    //增加服务商个人信息
    int addProviderInfo(int providerId);

	List<ProviderInfo> queryAll();
}
