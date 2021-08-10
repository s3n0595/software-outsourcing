package com.cykj.service.impl;

import com.cykj.bean.Demand;
import com.cykj.bean.UnionInfo;
import com.cykj.bean.Works;
import com.cykj.mapper.BusinessMapper;
import com.cykj.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guoquansen
 * @date 2021/8/7 11:21 上午
 */
@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public List<Works> queryAllWorks(int page, int pageSize) {
        return businessMapper.queryAllWorks(page,pageSize);
    }

    @Override
    public int queryWorksTotal() {
        return businessMapper.queryWorksTotal();
    }

    @Override
    public int updateStatus(int worksId, int auditStatus) {
        return businessMapper.updateStatus(worksId,auditStatus);
    }

    @Override
    public List<Works> queryWorksTitle(String worksTitle) {
        return businessMapper.queryWorksTitle(worksTitle);
    }

    @Override
    public List<Works> queryWorksByAudit(int auditStatus) {
        return businessMapper.queryWorksByAudit(auditStatus);
    }

    @Override
    public int deleteWorksList(int worksId) {
        return businessMapper.deleteWorksList(worksId);
    }

    @Override
    public List<UnionInfo> queryAllUnion() {
        return businessMapper.queryAllUnion();
    }

    @Override
    public int updateUnion(int unionId, int auditStatus) {
        return businessMapper.updateUnion(unionId,auditStatus);
    }

    @Override
    public List<UnionInfo> queryUnionName(String unionName) {
        return businessMapper.queryUnionName(unionName);
    }

    @Override
    public List<UnionInfo> queryUnionByAudit(int auditStatus) {
        return businessMapper.queryUnionByAudit(auditStatus);
    }

    @Override
    public int deleteUnion(int unionId) {
        return businessMapper.deleteUnion(unionId);
    }

    @Override
    public int getUnionTotal() {
        return businessMapper.getUnionTotal();
    }


}
