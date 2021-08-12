package com.cykj.service;

import com.cykj.bean.ProviderAccount;
import com.cykj.bean.TradeRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/10 17:47
 * @desc:
 */
public interface TradeRecordService {
    // 获取投标交易表
    List<TradeRecord> getTradeRecord();
    // 通过providerId查找服务商
    ProviderAccount getProvider(int providerId);
}
