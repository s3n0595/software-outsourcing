package com.cykj.service.impl;

import com.cykj.bean.ProviderAccount;
import com.cykj.bean.TradeRecord;
import com.cykj.mapper.TradeRecordMapper;
import com.cykj.service.TradeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/10 17:47
 * @desc:
 */
@Service
public class TradeRecordServiceImp implements TradeRecordService {
    @Autowired
    private TradeRecordMapper tradeRecordMapper;
    @Override
    public List<TradeRecord> getTradeRecord() {
        return tradeRecordMapper.getTradeRecord();
    }

    @Override
    public ProviderAccount getProvider(int providerId) {
        return tradeRecordMapper.getProvider(providerId);
    }
}
