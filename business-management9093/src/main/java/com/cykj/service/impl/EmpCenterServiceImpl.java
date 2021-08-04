package com.cykj.service.impl;

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
    public int updatePwd(int employerId, String employerPassword,String password) {
        return empCenterMapper.updatePwd(employerId,employerPassword,password);
    }
}
