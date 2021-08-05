package com.cykj.service.impl;

import com.cykj.bean.CapitalFlow;
import com.cykj.bean.Demand;
import com.cykj.bean.DemandType;
import com.cykj.mapper.EmpCenterMapper;
import com.cykj.service.EmpCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
}
