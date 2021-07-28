package com.cykj.service;

import com.cykj.bean.EmployerAccount;
import com.cykj.bean.EmployerInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmpService {
    List<EmployerAccount> emplist();
    int editEmp(EmployerAccount employerAccount);
    int delEmp(Integer eId);
    List<EmployerAccount> selEmplist(String name);
}
