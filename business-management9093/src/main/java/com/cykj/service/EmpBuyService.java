package com.cykj.service;

import com.cykj.bean.TradeWork;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: Jing
 * @date: 2021/8/8 14:11
 */
public interface EmpBuyService {
    int selBalance(Integer employerId);
    int addTradeWork(TradeWork tradeWork,Integer balance);
    TradeWork oldtradeWork(TradeWork tradeWork);
    List<Map<String, Object>> selbuyList(Integer employerId);
    int editState(TradeWork tradeWork);
}
