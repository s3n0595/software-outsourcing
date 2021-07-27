package com.cykj.service.impl;

import com.cykj.bean.ProviderAccount;
import com.cykj.mapper.ProviderMapper;
import com.cykj.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author guoquansen
 * @date 2021/7/27 10:14 下午
 */
@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderMapper providerMapper;

    @Override
    public int addProvider(ProviderAccount providerAccount) {
        return providerMapper.addProvider(providerAccount);
    }

    @Override
    public int queryProviderAccByTel(String phoneNumber) {
        return providerMapper.queryProviderAccByTel(phoneNumber);
    }

    @Override
    public int queryProviderAccByLog(ProviderAccount providerAccount) {
        return providerMapper.queryProviderAccByLog(providerAccount);
    }
}
