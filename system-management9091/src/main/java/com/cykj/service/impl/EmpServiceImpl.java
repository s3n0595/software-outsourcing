package com.cykj.service.impl;

import com.cykj.bean.EmployerAccount;
import com.cykj.mapper.EmpMapper;
import com.cykj.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Override
    public List<EmployerAccount> emplist() {
       List<EmployerAccount> emps=empMapper.emplist();
       return emps;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int editEmp(EmployerAccount employerAccount) {
        try{
            int i=empMapper.editEmp(employerAccount);
            int j=empMapper.editEmpInfo(employerAccount.getEmployerInfo());
            if(i>0 && j>0){
                return i;
            }else{
                return 0;
            }
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delEmp(Integer eId) {
        try {
            int i=empMapper.delEmp(eId);
            int j=empMapper.delEmpInfo(eId);
            if(i>0 && j>0){
                return i;
            }else{
                return 0;
            }
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }

    @Override
    public List<EmployerAccount> selEmplist(String name) {
        List<EmployerAccount> emps=empMapper.selEmplist(name);
        return emps;
    }
}
