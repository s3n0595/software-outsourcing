package com.cykj.service;

import com.cykj.bean.EmployerAccount;
import com.cykj.bean.EmployerInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmpService {
    List<EmployerAccount> emplist(Integer pageNo,Integer pageSize,String searchInfo);
    int countEmp(String searchInfo);
    int editEmp(EmployerAccount employerAccount);
    int delEmp(Integer eId);
    List<EmployerAccount> selEmplist(String name);
}
