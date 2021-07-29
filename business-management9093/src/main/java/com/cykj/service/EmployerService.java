package com.cykj.service;

import com.cykj.bean.EmployerAccount;

/**
 * @author guoquansen
 * @date 2021/7/26 1:35 下午
 */
public interface EmployerService {

    //增加用户
    int addUser(EmployerAccount employerAccount);

    //查询注册雇主
    int queryEmployerAccByTel(String phoneNumber);

    //查询登陆雇主
    int queryEmployerAccByLog(EmployerAccount employerAccount);

    //查找注册雇主ID姓名
    EmployerAccount queryEmployerIdName(String phoneNum);

    //增加雇主个人信息
    int addEmployerInfo(int employerId);

    //增加雇主个人信息到雇主故事表
    int addEmployerStory(int employerId);

    //增加雇主个人信息到曝光台
    int addEmployerExpose(int employerId);
}
