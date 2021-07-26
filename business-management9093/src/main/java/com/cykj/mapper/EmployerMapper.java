package com.cykj.mapper;

import com.cykj.bean.EmployerAccount;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author guoquansen
 * @date 2021/7/26 1:35 下午
 */
@Repository
public interface EmployerMapper {

    //增加用户
    int addUser(EmployerAccount employerAccount);
}
