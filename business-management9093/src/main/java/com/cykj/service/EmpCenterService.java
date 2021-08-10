package com.cykj.service;


import com.cykj.bean.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmpCenterService {
    int addNeed(Demand demand);
    List<Demand> nedds(int id);
    List<DemandType> ckList();
    int editNeed(Demand demand);
    int delNeed( int did);
    int updatePwd(int employerId,String employerPassword,String password);
    int addFlow(CapitalFlow capitalFlow);
    CapitalFlow seleEmpphone( String tradeNo);
    int seleEmpId(String phoneNumber);
    int editEmpBalance(int employerId,double balance);
    int updFlow(CapitalFlow capitalFlow);
    int selempBalance(int eid);
    int selproBalance(int providerId);
    int seleProId(String phoneNumber);
    int editProBalance( int providerId, double balance);
    int updateEmpEmail(EmployerInfo employerInfo);
    int updateHead(int employerId,String name);
    Map<String,Object> selempInfo( int employerId);
    int updateEmpName(EmployerAccount employerAccount);
    List<CreditDetails> selCredit(CreditDetails creditDetails);
}
