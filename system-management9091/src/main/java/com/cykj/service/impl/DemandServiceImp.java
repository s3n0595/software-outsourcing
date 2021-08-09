package com.cykj.service.impl;

import com.cykj.bean.Demand;
import com.cykj.mapper.DemandMapper;
import com.cykj.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/9 10:16
 * @desc:
 */
@Service
public class DemandServiceImp implements DemandService {
    @Autowired
    private DemandMapper demandMapper;
    @Override
    public List<Demand> getDemandList() {
        return demandMapper.getDemandList();
    }

    @Override
    public List<Demand> getSearchDemand(String employerName) {
        return demandMapper.getSearchDemand(employerName);
    }

    @Override
    public List<Demand> getSearchState(int demandStatus) {
        return demandMapper.getSearchState(demandStatus);
    }

    @Override
    public int updateDemandState(int demandId, int demandStatus) {
        return demandMapper.updateDemandState(demandId,demandStatus);
    }

    @Override
    public int deleteDemandList(int demandId) {
        return demandMapper.deleteDemandList(demandId);
    }
}
