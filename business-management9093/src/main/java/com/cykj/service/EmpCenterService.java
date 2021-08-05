package com.cykj.service;


import com.cykj.bean.CapitalFlow;
import com.cykj.bean.Demand;
import com.cykj.bean.DemandType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
}
