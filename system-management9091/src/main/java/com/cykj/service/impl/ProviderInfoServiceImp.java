package com.cykj.service.impl;

import com.cykj.mapper.ProviderInfoMapper;
import com.cykj.service.ProviderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/13 18:50
 * @desc:
 */
@Service
public class ProviderInfoServiceImp implements ProviderInfoService {
    @Autowired
    private ProviderInfoMapper providerInfoMapper;
    @Override
    public int updateCredit(int providerId,int credit,double balance) {
        return providerInfoMapper.updateCredit(providerId,credit,balance);
    }

    @Override
    public int updateEBalance(int employerId, double balance) {
        return providerInfoMapper.updateEBalance(employerId,balance);
    }

    @Override
    public int addCreditDetails(int userId, String remarks, String creditTime, String type, String score) {
        return providerInfoMapper.addCreditDetails(userId,remarks,creditTime,type,score);
    }

    @Override
    public int addCapitalFlow(String tradeType, String phoneNumber, String tradeContent, int tradeCapital, String tradeTime, String tradeState, String type) {
        return providerInfoMapper.addCapitalFlow(tradeType,phoneNumber,tradeContent,tradeCapital,tradeTime,tradeState,type);
    }
}
