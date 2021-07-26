package com.cykj.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author guoquansen
 * @date 2021/7/26 1:35 下午
 */
@Mapper
public interface EmployerMapper {

    //增加用户
    int addUser(EmployerAccount employerAccount);
}
