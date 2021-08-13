package com.cykj.service;

import org.apache.ibatis.annotations.Param;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/13 18:49
 * @desc:
 */
public interface ProviderInfoService {
    // 修改信誉分
    int updateCredit(int providerId,int credit,double balance);
    // 修改买家余额
    int updateEBalance(int employerId,double balance);
    // 添加信誉记录
    int addCreditDetails(int userId,String remarks,String creditTime,String type,String score);
    // 添加流水表
    int addCapitalFlow(String tradeType,String phoneNumber,String tradeContent,int tradeCapital,String tradeTime,String tradeState,String type);
}
