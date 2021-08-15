package com.cykj.service.impl;

import com.cykj.bean.TradeWork;
import com.cykj.mapper.TradeWorksMapper;
import com.cykj.service.TradeWorksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/10 21:29
 * @desc:
 */
@Service
public class TradeWorksServiceImp implements TradeWorksService {
    @Autowired
    private TradeWorksMapper tradeWorksMapper;
    @Override
    public List<TradeWork> getTradeWordList() {
        return tradeWorksMapper.getTradeWordList();
    }

    @Override
    public int updateTradeStatus(int tradeWorksId, int tradeStatus) {
        return tradeWorksMapper.updateTradeStatus(tradeWorksId,tradeStatus);
    }
}
