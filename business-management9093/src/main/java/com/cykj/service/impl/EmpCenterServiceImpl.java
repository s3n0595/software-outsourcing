package com.cykj.service.impl;

import com.cykj.bean.*;
import com.cykj.mapper.EmpCenterMapper;
import com.cykj.service.EmpCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class EmpCenterServiceImpl implements EmpCenterService {
    @Autowired
    private EmpCenterMapper empCenterMapper;
    @Override
    public int addNeed(Demand demand) {
        int i=empCenterMapper.addNeed(demand);
        return i;
    }

    @Override
    public List<Demand> nedds(int id) {
        List<Demand> ns=empCenterMapper.nedds(id);
        return ns;
    }

    @Override
    public List<DemandType> ckList() {
        List<DemandType> cks= empCenterMapper.ckList();
        return cks;
    }

    @Override
    public int editNeed(Demand demand) {
        int i=empCenterMapper.editNeed(demand);
        return i;
    }

    @Override
    public int delNeed(int did) {
        int i=empCenterMapper.delNeed(did);
        return i;
    }

    @Override
    public int updatePwd(int employerId, String employerPassword,String password) {
        return empCenterMapper.updatePwd(employerId,employerPassword,password);
    }

    @Override
    public int addFlow(CapitalFlow capitalFlow) {
        int i=empCenterMapper.addFlow(capitalFlow);
        return i;
    }

    @Override
    public CapitalFlow seleEmpphone(String tradeNo) {
        CapitalFlow capitalFlow= empCenterMapper.seleEmpphone(tradeNo);
        return capitalFlow;
    }

    @Override
    public int seleEmpId(String phoneNumber) {
        int employerId=empCenterMapper.seleEmpId(phoneNumber);
        return employerId;
    }

    @Override
    public int editEmpBalance(int employerId, double balance) {
        int i=empCenterMapper.editEmpBalance(employerId,balance);
        return i;
    }

    @Override
    public int updFlow(CapitalFlow capitalFlow) {
        int i=empCenterMapper.updFlow(capitalFlow);
        return i;
    }

    @Override
    public int selempBalance(int eid) {
        int i=empCenterMapper.selempBalance(eid);
        return i;
    }

    @Override
    public int selproBalance(int providerId) {
        return empCenterMapper.selproBalance(providerId);
    }

    @Override
    public int seleProId(String phoneNumber) {
        return empCenterMapper.seleProId(phoneNumber);
    }

    @Override
    public int editProBalance(int providerId, double balance) {
        return empCenterMapper.editProBalance(providerId,balance);
    }

    @Override
    public int updateEmpEmail(EmployerInfo employerInfo) {
        return empCenterMapper.updateEmpEmail(employerInfo);
    }

    @Override
    public int updateProEmail(ProviderInfo providerInfo) {
        return empCenterMapper.updateProEmail(providerInfo);
    }

    @Override
    public int updateHead(int employerId, String name) {
        return empCenterMapper.updateHead(employerId,name);
    }

    @Override
    public int updateproHead(int providerId, String name) {
        return empCenterMapper.updateproHead(providerId,name);
    }

    @Override
    public Map<String, Object> selempInfo(int employerId) {
        return empCenterMapper.selempInfo(employerId);
    }

    @Override
    public Map<String, Object> selproInfo(int providerId) {
        return empCenterMapper.selproInfo(providerId);
    }

    @Override
    public int updateEmpName(EmployerAccount employerAccount) {
        return empCenterMapper.updateEmpName(employerAccount);
    }

    @Override
    public int updateProName(ProviderAccount providerAccount) {
        return empCenterMapper.updateProName(providerAccount);
    }

    @Override
    public List<CreditDetails> selCredit(CreditDetails creditDetails) {
        return empCenterMapper.selCredit(creditDetails);
    }

    @Override
    public Map<String, Object> selEmpInfo(int userid) {
        return empCenterMapper.selEmpInfo(userid);
    }

    @Override
    public Map<String, Object> selProInfo(int userid) {
        return empCenterMapper.selProInfo(userid);
    }

    @Override
    public int empSetPwd(int userid, int transactionPwd) {
        return empCenterMapper.empSetPwd(userid,transactionPwd);
    }

    @Override
    public int proSetPwd(int userid, int transactionPwd) {
        return empCenterMapper.proSetPwd(userid,transactionPwd);
    }

    @Override
    public int empEditPwd(int userid, int oldpwd, int opwd) {
        return empCenterMapper.empEditPwd(userid, oldpwd, opwd);
    }

    @Override
    public int proEditPwd(int userid, int oldpwd, int opwd) {
        return empCenterMapper.proEditPwd(userid, oldpwd, opwd);
    }

    @Override
    public List<Map<String, Object>> selempAllFlow(CapitalFlow capitalFlow) {
        return empCenterMapper.selempAllFlow(capitalFlow);
    }
}
