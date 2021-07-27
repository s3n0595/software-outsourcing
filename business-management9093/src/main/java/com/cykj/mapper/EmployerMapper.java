package com.cykj.mapper;

import com.cykj.bean.EmployerAccount;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author guoquansen
 * @date 2021/7/26 1:35 下午
 */
@Mapper
public interface EmployerMapper {

    //增加用户
    int addUser(EmployerAccount employerAccount);

    //查询注册雇主
    int queryEmployerAccByTel(String phoneNumber);

    //查询登陆雇主
    int queryEmployerAccByLog(EmployerAccount employerAccount);

}
