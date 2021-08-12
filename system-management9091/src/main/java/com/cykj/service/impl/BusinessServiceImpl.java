package com.cykj.service.impl;

import com.cykj.bean.Adviser;
import com.cykj.bean.ProviderAccount;
import com.cykj.bean.UnionInfo;
import com.cykj.bean.Works;
import com.cykj.mapper.BusinessMapper;
import com.cykj.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author guoquansen
 * @date 2021/8/7 11:21 上午
 */
@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public List<Works> queryAllWorks() {
        return businessMapper.queryAllWorks();
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
    public List<Adviser> queryAllAdviser() {
        return businessMapper.queryAllAdviser();
    }

    @Override
    public List<ProviderAccount> queryIdByName(String providerName) {
        return businessMapper.queryIdByName(providerName);
    }

    @Override
    public Adviser queryAdviserByProviderId(int providerId) {
        return businessMapper.queryAdviserByProviderId(providerId);
    }


    @Override
    public int updateAdviser(int adviserId, int auditStatus) {
        return businessMapper.updateAdviser(adviserId,auditStatus);
    }

    @Override
    public List<Adviser> queryAdviserState(int auditStatus) {
        return businessMapper.queryAdviserState(auditStatus);
    }

    @Override
    public int deleteAdviser(int adviserId) {
        return businessMapper.deleteAdviser(adviserId);
    }


}
