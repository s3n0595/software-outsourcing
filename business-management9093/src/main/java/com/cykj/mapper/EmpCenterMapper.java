package com.cykj.mapper;


import com.cykj.bean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpCenterMapper {
    int addNeed(Demand demand);//添加雇主需求
    List<Demand> nedds(@Param("employerId") int id);//雇主过往需求列表
    List<DemandType> ckList();//获取需求类型表
    int editNeed(Demand demand);//修改雇主需求信息
    int delNeed(@Param("demandId") int did);//删除需求
    int addFlow(CapitalFlow capitalFlow);//添加充值记录
    CapitalFlow seleEmpphone(@Param("tradeNo") String tradeNo);//通过订单号查找充值对象
    int seleEmpId(@Param("phoneNumber") String phoneNumber);//通过手机号查找雇主ID
    int updFlow(CapitalFlow capitalFlow);//修改充值记录
    int editEmpBalance(@Param("employerId") int employerId,@Param("balance") double balance);//修改雇主余额
    int selempBalance(@Param("employerId") int eid);//查询雇主账户余额
    int updatePwd(@Param("employerId") int employerId,@Param("employerPassword") String employerPassword,@Param("password") String password);
    int updateEmpEmail(EmployerInfo employerInfo);//雇主修改邮箱
    int updateProEmail(ProviderInfo providerInfo);//服务商修改邮箱
    int updateEmpName(EmployerAccount employerAccount);//雇主修改名称
    int updateProName(ProviderAccount providerAccount);//服务商修改名称
    List<CreditDetails> selCredit(CreditDetails creditDetails);//查询信用分详细
    int selproBalance(@Param("providerId") int providerId);//查询服务商余额
    int seleProId(@Param("phoneNumber") String phoneNumber);//通过手机号查找服务商ID
    int editProBalance(@Param("providerId") int providerId,@Param("balance") double balance);//修改服务商余额
    int updateHead(@Param("employerId")int employerId,@Param("headPath") String name);//用户上传头像
    int updateproHead(@Param("providerId")int providerId,@Param("headPath") String name);//服务商上传头像
    Map<String,Object> selempInfo(@Param("employerId") int employerId);//个人中心雇主基本信息查询
    Map<String,Object> selproInfo(@Param("providerId") int providerId);//个人中心服务商基本信息查询
    Map<String,Object> selEmpInfo(@Param("id") int userid);//雇主交易密码初始查询邮箱
    Map<String,Object> selProInfo(@Param("id") int userid);//服务商交易密码初始查询邮箱
    int empSetPwd(@Param("id") int userid,@Param("transactionPwd") int transactionPwd);//雇主设置交易密码
    int proSetPwd(@Param("id") int userid,@Param("transactionPwd") int transactionPwd);//服务商设置交易密码
    int empEditPwd(@Param("id") int userid,@Param("oldpwd") int oldpwd,@Param("opwd") int opwd);//雇主修改交易密码
    int proEditPwd(@Param("id") int userid,@Param("oldpwd") int oldpwd,@Param("opwd") int opwd);//服务商修改交易密码
    List<Map<String, Object>> selempAllFlow(CapitalFlow capitalFlow);//查询雇主流水
}
