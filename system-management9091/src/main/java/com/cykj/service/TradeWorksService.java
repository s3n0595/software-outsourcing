package com.cykj.service;

import com.cykj.bean.TradeWork;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/10 21:29
 * @desc:
 */
public interface TradeWorksService {
    // 获取作品交易列表
    List<TradeWork> getTradeWordList();
}
