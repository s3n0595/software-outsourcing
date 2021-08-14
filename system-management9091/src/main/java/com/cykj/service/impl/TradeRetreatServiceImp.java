package com.cykj.service.impl;

import com.cykj.bean.TradeRetreat;
import com.cykj.mapper.TradeRetreatMapper;
import com.cykj.service.TradeRetreatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/13 15:59
 * @desc:
 */
@Service
public class TradeRetreatServiceImp implements TradeRetreatService {
    @Autowired
    private TradeRetreatMapper tradeRetreatMapper;
    @Override
    public List<TradeRetreat> getTradeRetreatList() {
        return tradeRetreatMapper.getTradeRetreatList();
    }

    @Override
    public int updateTreatStatus(int tradeRetreatId,int retreatStatus) {
        return tradeRetreatMapper.updateTreatStatus(tradeRetreatId,retreatStatus);
    }
}
